$(document).ready(function () {
  const urlParams = new URLSearchParams(window.location.search);
  const nomorRegistrasi = urlParams.get("nomorRegistrasi");
  const form = $("#dataForm");

  if (nomorRegistrasi) {
    fetchDataForEdit(nomorRegistrasi);
  }

  form.find(":input").on("input", function () {
    const element = $(this);
    if (element[0].checkValidity()) {
      element.removeClass("is-invalid");
      hideAllErrors(element);
    }
  });
});

function fetchDataForEdit(nomorRegistrasi) {
  axios
    .get(`http://localhost:9000/kendaraan/${nomorRegistrasi}`)
    .then((response) => {
      if (response.data.status === 200) {
        const kendaraan = response.data.data;
        $("#noRegistrasi").val(kendaraan.nomorRegistrasi);
        $("#namaPemilik").val(kendaraan.namaPemilik);
        $("#merkKendaraan").val(kendaraan.merkKendaraan);
        $("#alamatPemilik").val(kendaraan.alamat);
        $("#tahunPembuatan").val(kendaraan.tahunPembuatan);
        $("#kapasitasSilinder").val(kendaraan.kapasitasSilinder);
        $("#warnaKendaraan").val(kendaraan.warnaKendaraan);
        $("#bahanBakar").val(kendaraan.bahanBakar);
      } else {
        console.error("Failed to fetch data for edit:", response.data.message);
      }
    })
    .catch((error) => {
      console.error("Error fetching data for edit:", error);
    });
}

function simpanData() {
  const form = $("#dataForm");
  const urlParams = new URLSearchParams(window.location.search);
  const nomorRegistrasi = urlParams.get("nomorRegistrasi");
  let isFormValid = true;

  form.find(":input").each(function () {
    const element = $(this);
    hideAllErrors(element);

    if (!element[0].checkValidity()) {
      element.addClass("is-invalid");
      isFormValid = false;

      if (element[0].validity.valueMissing) {
        showError(element, "required");
      } else if (element[0].validity.tooShort) {
        showError(element, "minlength");
      } else if (element[0].validity.patternMismatch) {
        showError(element, "pattern");
      }
    }
  });

  if (!isFormValid) {
    return;
  }

  const data = {
    namaPemilik: $("#namaPemilik").val(),
    merkKendaraan: $("#merkKendaraan").val(),
    alamat: $("#alamatPemilik").val(),
    tahunPembuatan: $("#tahunPembuatan").val(),
    kapasitasSilinder: $("#kapasitasSilinder").val(),
    warnaKendaraan: $("#warnaKendaraan").val(),
    bahanBakar: $("#bahanBakar").val(),
  };

  axios
    .put(`http://localhost:9000/kendaraan/${nomorRegistrasi}`, data)
    .then((response) => {
      localStorage.setItem("editSuccessMessage", "Data Kendaraan berhasil diperbaharui!");
      window.location.href = "index.html";
    })
    .catch((error) => {
      if (error.response) {
        localStorage.setItem("toastMessage", "Gagal menyimpan data. Coba lagi!");
      }
      window.location.href = "editDataKendaraan.html";
    });
}

function hideAllErrors(element) {
  element.parent().find(".invalid-feedback").hide();
}

function showError(element, errorType) {
  element.parent().find(`.invalid-feedback[data-error="${errorType}"]`).show();
}

function kembali() {
  window.location.href = "index.html";
}

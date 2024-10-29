$(document).ready(function () {
  const form = $("#dataForm");

  form.find(":input").on("input", function () {
    const element = $(this);
    if (element[0].checkValidity()) {
      element.removeClass("is-invalid");
      hideAllErrors(element);
    }
  });
});

function simpanData() {
  const form = $("#dataForm");
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
    nomorRegistrasi: $("#noRegistrasi").val(),
    namaPemilik: $("#namaPemilik").val(),
    merkKendaraan: $("#merkKendaraan").val(),
    alamat: $("#alamatPemilik").val(),
    tahunPembuatan: $("#tahunPembuatan").val(),
    kapasitasSilinder: $("#kapasitasSilinder").val(),
    warnaKendaraan: $("#warnaKendaraan").val(),
    bahanBakar: $("#bahanBakar").val(),
  };

  axios
    .post("http://localhost:9000/kendaraan", data)
    .then((response) => {
      localStorage.setItem("successMessage", "Data berhasil disimpan!");
      console.log(response.data);
      window.location.href = "index.html";
    })
    .catch((error) => {
      if (error.response && error.response.status === 400) {
        localStorage.setItem("failedMessage", "Nomor Registrasi sudah terdaftar!"); // Simpan pesan error
      } else {
        localStorage.setItem("toastMessage", "Gagal menyimpan data. Coba lagi!");
      }
      window.location.href = "addDataKendaraan.html";
    });
}

function hideAllErrors(element) {
  element.parent().find(".invalid-feedback").hide();
}

function showError(element, errorType) {
  const feedback = element.parent().find(`.invalid-feedback[data-error="${errorType}"]`);
  if (feedback.length) {
    feedback.show();
  }
}

function kembali() {
  window.location.href = "index.html";
}

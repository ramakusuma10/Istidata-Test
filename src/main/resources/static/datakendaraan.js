$(document).ready(function () {
  const urlParams = new URLSearchParams(window.location.search);
  const nomorRegistrasi = urlParams.get("nomorRegistrasi");
  const namaPemilik = urlParams.get("namaPemilik");

  if (nomorRegistrasi && namaPemilik) {
    searchKendaraan(nomorRegistrasi, namaPemilik);
    $("#noRegistrasi").val(nomorRegistrasi);
    $("#namaPemilik").val(namaPemilik);
  } else {
    fetchDataKendaraan();
  }

  $("#addBtn").click(function () {
    window.location.href = "addDataKendaraan.html";
  });

  $("#searchBtn").click(function () {
    const nomorRegistrasi = $("#noRegistrasi").val();
    const namaPemilik = $("#namaPemilik").val();
    if (nomorRegistrasi && namaPemilik) {
      window.location.href = `index.html?nomorRegistrasi=${nomorRegistrasi}&namaPemilik=${namaPemilik}`;
    } else {
      localStorage.setItem(
        "FailedSearch",
        "Harap isi kedua field No Registrasi dan Nama Pemilik untuk melakukan pencarian."
      );
      window.location.href = "index.html";
    }
  });

  $("#resetBtn").click(resetSearch);
});

function fetchDataKendaraan() {
  $.get("http://localhost:9000/kendaraan", function (response) {
    if (response.status === 200) {
      const kendaraanData = response.data;
      const tableBody = $("#tableBody");
      tableBody.empty(); // Hapus data tabel yang ada

      kendaraanData.forEach((item, index) => {
        const displayWarnaKendaraan = item.warnaKendaraan.replace(/_/g, "-");
        const row = `
          <tr>
            <td class="text-table-center">${index + 1}</td>
            <td class="text-table-center">${item.nomorRegistrasi}</td>
            <td>${item.namaPemilik}</td>
            <td class="text-table-center">${item.merkKendaraan}</td>
            <td class="text-table-center">${item.tahunPembuatan}</td>
            <td class="text-table-center">${item.kapasitasSilinder} cc</td>
            <td>${displayWarnaKendaraan}</td>
            <td class="text-table-center">${item.bahanBakar}</td>
            <td class="text-center">
              <button class="btn btn-sm btn-warning" onClick="DetailData('${item.nomorRegistrasi}')">Detail</button>
              <button class="btn btn-sm btn-primary" onClick="EditData('${item.nomorRegistrasi}')">Edit</button>
              <button class="btn btn-sm btn-danger" onClick="confirmDelete('${item.nomorRegistrasi}')">Delete</button>
            </td>
          </tr>
        `;
        tableBody.append(row);
      });
    } else {
      console.error("Failed to fetch data:", response.message);
    }
  }).fail(function (error) {
    console.error("Error fetching data:", error);
  });
}

function EditData(nomorRegistrasi) {
  window.location.href = `editDataKendaraan.html?nomorRegistrasi=${nomorRegistrasi}`;
}

function DetailData(nomorRegistrasi) {
  window.location.href = `detailDataKendaraan.html?nomorRegistrasi=${nomorRegistrasi}`;
}

function confirmDelete(nomorRegistrasi) {
  console.log("Modal Terpencet");
  $("#deleteMessage").text(`Anda yakin menghapus data ${nomorRegistrasi}?`);
  const deleteModal = new bootstrap.Modal($("#deleteModal")[0]);
  deleteModal.show();

  $("#confirmDeleteBtn").off("click").on("click", function () {
    deleteData(nomorRegistrasi);
    deleteModal.hide();
  });
}

function deleteData(nomorRegistrasi) {
  $.ajax({
    url: `http://localhost:9000/kendaraan/${nomorRegistrasi}`,
    type: "DELETE",
    success: function (response) {
      if (response.status === 200) {
        fetchDataKendaraan(); // Refresh data
      } else {
        console.error("Failed to delete data:", response.message);
      }
    },
    error: function (error) {
      console.error("Error deleting data:", error);
    }
  });
}

function searchKendaraan(nomorRegistrasi, namaPemilik) {
  $.get(
    `http://localhost:9000/kendaraan/search?nomorRegistrasi=${nomorRegistrasi}&namaPemilik=${namaPemilik}`,
    function (response) {
      const kendaraanData = response.data;
      const tableBody = $("#tableBody");
      tableBody.empty(); // Bersihkan data tabel

      kendaraanData.forEach((item, index) => {
        const displayWarnaKendaraan = item.warnaKendaraan.replace(/_/g, "-");
        const row = `
          <tr>
            <td class="text-table-center">${index + 1}</td>
            <td class="text-table-center">${item.nomorRegistrasi}</td>
            <td>${item.namaPemilik}</td>
            <td class="text-table-center">${item.merkKendaraan}</td>
            <td class="text-table-center">${item.tahunPembuatan}</td>
            <td class="text-table-center">${item.kapasitasSilinder} cc</td>
            <td>${displayWarnaKendaraan}</td>
            <td class="text-table-center">${item.bahanBakar}</td>
            <td class="text-center">
              <button class="btn btn-sm btn-primary" onClick="DetailData('${item.nomorRegistrasi}')">Detail</button>
              <button class="btn btn-sm btn-warning" onClick="EditData('${item.nomorRegistrasi}')">Edit</button>
              <button class="btn btn-sm btn-danger" onClick="confirmDelete('${item.nomorRegistrasi}')">Delete</button>
            </td>
          </tr>
        `;
        tableBody.append(row);
      });
      $("#resetBtn").show(); // Tampilkan tombol Reset setelah pencarian berhasil
    }
  ).fail(function (error) {
    if (error.status === 404) {
      localStorage.setItem("failedSearchMessage", "Data Tidak Ditemukan");
    } else {
      localStorage.setItem("toastMessage", "Gagal menyimpan data. Coba lagi!");
    }
    window.location.href = "index.html";
  });
}

function resetSearch() {
  $("#noRegistrasi").val("");
  $("#namaPemilik").val("");
  fetchDataKendaraan();
  $("#resetBtn").hide(); // Sembunyikan tombol Reset
}

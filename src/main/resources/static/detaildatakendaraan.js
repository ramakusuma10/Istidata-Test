$(document).ready(function () {
    const urlParams = new URLSearchParams(window.location.search);
    const nomorRegistrasi = urlParams.get("nomorRegistrasi");
  
    if (nomorRegistrasi) {
      // Panggil fungsi untuk mengisi data berdasarkan nomorRegistrasi
      fetchData(nomorRegistrasi);
    }
  
});
function fetchData(nomorRegistrasi) {
  $.ajax({
    url: `http://localhost:9000/kendaraan/${nomorRegistrasi}`,
    method: "GET",
    success: function (response) {
      if (response.status === 200) {
        const kendaraan = response.data;
        $("#noRegistrasi").val(kendaraan.nomorRegistrasi);
        $("#namaPemilik").val(kendaraan.namaPemilik);
        $("#merkKendaraan").val(kendaraan.merkKendaraan);
        $("#alamatPemilik").val(kendaraan.alamat);
        $("#tahunPembuatan").val(kendaraan.tahunPembuatan);
        $("#kapasitasSilinder").val(kendaraan.kapasitasSilinder);
        $("#warnaKendaraan").val(kendaraan.warnaKendaraan);
        $("#bahanBakar").val(kendaraan.bahanBakar);
      } else {
        console.error("Failed to fetch data for edit:", response.message);
      }
    },
    error: function (error) {
      console.error("Error fetching data for edit:", error);
    },
  });
}
function kembali() {
        window.location.href = "index.html";
}
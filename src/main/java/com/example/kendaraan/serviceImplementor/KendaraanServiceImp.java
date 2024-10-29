package com.example.kendaraan.serviceImplementor;

import com.example.kendaraan.dto.KendaraanDTO;
import com.example.kendaraan.model.KendaraanModel;
import com.example.kendaraan.service.KendaraanService;
import com.example.kendaraan.repository.KendaraanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

@Service
public class KendaraanServiceImp implements KendaraanService {

    @Autowired
    private KendaraanRepository kendaraanRepository;

    @Override
    public KendaraanModel createKendaraan(KendaraanDTO kendaraanDTO) {
        if (kendaraanDTO.getNomorRegistrasi() == null || kendaraanDTO.getNomorRegistrasi().isEmpty()) {
            throw new IllegalArgumentException("Nomor Registrasi wajib diisi");
        }
        if (kendaraanDTO.getNomorRegistrasi().length() != 10) {
            throw new IllegalArgumentException("Nomor Registrasi harus 10 karakter");
        }
        if (kendaraanRepository.existsByNomorRegistrasi(kendaraanDTO.getNomorRegistrasi())) {
            throw new IllegalArgumentException("Nomor Registrasi sudah terdaftar");
        }
        if (kendaraanDTO.getNamaPemilik() == null || kendaraanDTO.getNamaPemilik().isEmpty()) {
            throw new IllegalArgumentException("Nama Pemilik Harus diisi");
        }
        if (kendaraanDTO.getAlamat() == null || kendaraanDTO.getAlamat().isEmpty()) {
            throw new IllegalArgumentException("Nama Alamat Harus diisi");
        }
        if (kendaraanDTO.getMerkKendaraan() == null || kendaraanDTO.getMerkKendaraan().isEmpty()) {
            throw new IllegalArgumentException("Merk Kendaraan harus diisi");
        }
        if (kendaraanDTO.getTahunPembuatan() == 0) {
            throw new IllegalArgumentException("Tahun Pembuatan harus diisi");
        }
        String tahunPembuatanString = String.valueOf(kendaraanDTO.getTahunPembuatan());
        if (tahunPembuatanString.length() != 4) {
            throw new IllegalArgumentException("Tahun Pembuatan harus sesuai format (yyyy)");
        }
        if (!tahunPembuatanString.matches("\\d+")) {
            throw new IllegalArgumentException("Tahun Pembuatan tidak boleh huruf");
        }
        if (kendaraanDTO.getKapasitasSilinder() == 0) {
            throw new IllegalArgumentException("Kapasitas Silinder harus diisi");
        }
        if (kendaraanDTO.getWarnaKendaraan() == null) {
            throw new IllegalArgumentException("Warna harus diisi");
        }
        if (kendaraanDTO.getBahanBakar() == null || kendaraanDTO.getBahanBakar().isEmpty()) {
            throw new IllegalArgumentException("Bahan bakar harus diisi");
        }
        KendaraanModel kendaraan = new KendaraanModel();
        kendaraan.setNomorRegistrasi(kendaraanDTO.getNomorRegistrasi());
        kendaraan.setNamaPemilik(kendaraanDTO.getNamaPemilik());
        kendaraan.setAlamat(kendaraanDTO.getAlamat());
        kendaraan.setMerkKendaraan(kendaraanDTO.getMerkKendaraan());
        kendaraan.setTahunPembuatan(kendaraanDTO.getTahunPembuatan());
        kendaraan.setKapasitasSilinder(kendaraanDTO.getKapasitasSilinder());
        kendaraan.setWarnaKendaraan(kendaraanDTO.getWarnaKendaraan());
        kendaraan.setBahanBakar(kendaraanDTO.getBahanBakar());
        KendaraanModel savedKendaraan = kendaraanRepository.save(kendaraan);
        return savedKendaraan;
    }

    @Override
    public KendaraanDTO getKendaraanByNomorRegistrasi(String nomorRegistrasi) {
        KendaraanModel kendaraan = kendaraanRepository.findByNomorRegistrasi(nomorRegistrasi)
                .orElseThrow(() -> new RuntimeException("Kendaraan tidak ditemukan"));
        // Mengonversi KendaraanModel ke KendaraanDTO
        return new KendaraanDTO(
                kendaraan.getNomorRegistrasi(),
                kendaraan.getNamaPemilik(),
                kendaraan.getAlamat(),
                kendaraan.getMerkKendaraan(),
                kendaraan.getTahunPembuatan(),
                kendaraan.getKapasitasSilinder(),
                kendaraan.getWarnaKendaraan(),
                kendaraan.getBahanBakar()
        );
    }

    @Override
    public KendaraanModel updateKendaraan(String nomorRegistrasi, KendaraanDTO kendaraanDTO) {
        KendaraanModel existingKendaraan = kendaraanRepository.findByNomorRegistrasi(nomorRegistrasi)
                .orElseThrow(() -> new NoSuchElementException("Nomor Registrasi dan Kendaraan tidak ditemukan"));

        if (kendaraanDTO.getNamaPemilik() == null || kendaraanDTO.getNamaPemilik().isEmpty()) {
            throw new IllegalArgumentException("Nama Pemilik Harus diisi");
        }
        if (kendaraanDTO.getAlamat() == null || kendaraanDTO.getAlamat().isEmpty()) {
            throw new IllegalArgumentException("Nama Alamat Harus diisi");
        }
        if (kendaraanDTO.getMerkKendaraan() == null || kendaraanDTO.getMerkKendaraan().isEmpty()) {
            throw new IllegalArgumentException("Merk Kendaraan harus diisi");
        }
        if (kendaraanDTO.getTahunPembuatan() == 0) {
            throw new IllegalArgumentException("Tahun Pembuatan harus diisi");
        }
        String tahunPembuatanString = String.valueOf(kendaraanDTO.getTahunPembuatan());
        if (tahunPembuatanString.length() != 4) {
            throw new IllegalArgumentException("Tahun Pembuatan harus sesuai format (yyyy)");
        }
        if (!tahunPembuatanString.matches("\\d+")) {
            throw new IllegalArgumentException("Tahun Pembuatan tidak boleh huruf");
        }
        if (kendaraanDTO.getKapasitasSilinder() == 0) {
            throw new IllegalArgumentException("Kapasitas Silinder harus diisi");
        }
        if (kendaraanDTO.getWarnaKendaraan() == null) {
            throw new IllegalArgumentException("Warna harus diisi");
        }
        if (kendaraanDTO.getBahanBakar() == null || kendaraanDTO.getBahanBakar().isEmpty()) {
            throw new IllegalArgumentException("Bahan bakar harus diisi");
        }

        existingKendaraan.setNamaPemilik(kendaraanDTO.getNamaPemilik());
        existingKendaraan.setAlamat(kendaraanDTO.getAlamat());
        existingKendaraan.setMerkKendaraan(kendaraanDTO.getMerkKendaraan());
        existingKendaraan.setTahunPembuatan(kendaraanDTO.getTahunPembuatan());
        existingKendaraan.setKapasitasSilinder(kendaraanDTO.getKapasitasSilinder());
        existingKendaraan.setWarnaKendaraan(kendaraanDTO.getWarnaKendaraan());
        existingKendaraan.setBahanBakar(kendaraanDTO.getBahanBakar());

        return kendaraanRepository.save(existingKendaraan);
    }

    @Override
    public void deleteKendaraan(String nomorRegistrasi) {
        Optional<KendaraanModel> kendaraanOpt = kendaraanRepository.findById(nomorRegistrasi);
        if (!kendaraanOpt.isPresent()) {
            throw new NoSuchElementException("Kendaraan dengan nomor registrasi " + nomorRegistrasi + " tidak ditemukan.");
        }
        kendaraanRepository.delete(kendaraanOpt.get());
    }

    @Override
    public List<KendaraanModel> searchKendaraan(String nomorRegistrasi, String namaPemilik) {
        if (StringUtils.hasText(nomorRegistrasi) && StringUtils.hasText(namaPemilik)) {
            return kendaraanRepository.findByNomorRegistrasiAndNamaPemilik(nomorRegistrasi, namaPemilik);
        } else if (StringUtils.hasText(namaPemilik)) {
            return kendaraanRepository.findByNamaPemilik(namaPemilik);
        } else {
            return kendaraanRepository.findAll();
        }
    }

    @Override
    public List<KendaraanModel> getAllKendaraan() {
        return kendaraanRepository.findAll();
    }
}
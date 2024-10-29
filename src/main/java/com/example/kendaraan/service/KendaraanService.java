package com.example.kendaraan.service;

import com.example.kendaraan.dto.KendaraanDTO;
import com.example.kendaraan.model.KendaraanModel;

import java.util.List;

public interface KendaraanService {
    KendaraanModel createKendaraan(KendaraanDTO kendaraanDTO);
    KendaraanDTO getKendaraanByNomorRegistrasi(String nomorRegistrasi);
    KendaraanModel updateKendaraan(String NomorRegistrasi, KendaraanDTO kendaraanDTO);
    void deleteKendaraan(String nomorRegistrasi);
    List<KendaraanModel> getAllKendaraan();
    List<KendaraanModel> searchKendaraan(String nomorRegistrasi, String namaPemilik);
}
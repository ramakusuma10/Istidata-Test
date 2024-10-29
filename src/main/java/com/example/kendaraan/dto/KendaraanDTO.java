package com.example.kendaraan.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.example.kendaraan.model.WarnaKendaraan;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class KendaraanDTO {
    private String nomorRegistrasi;
    private String namaPemilik;
    private String alamat;
    private String merkKendaraan;
    private int tahunPembuatan;
    private int kapasitasSilinder;
    private WarnaKendaraan warnaKendaraan;
    private String bahanBakar;
}
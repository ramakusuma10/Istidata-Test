package com.example.kendaraan.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "kendaraan")
public class KendaraanModel {

    @Id
    @Column(name = "nomor_registrasi", nullable = false, unique = true)
    private String nomorRegistrasi;

    @Column(name = "nama_pemilik", nullable = false)
    private String namaPemilik;

    @Column(name = "alamat", nullable = false)
    private String alamat;

    @Column(name = "merk_kendaraan", nullable = false)
    private String merkKendaraan;

    @Column(name = "tahun_pembuatan", nullable = false, length = 4)
    private int tahunPembuatan;

    @Column(name = "kapasitas_silinder", nullable = false)
    private int kapasitasSilinder;

    @Enumerated(EnumType.STRING)
    @Column(name = "warna_kendaraan", nullable = false)
    private WarnaKendaraan warnaKendaraan;

    @Column(name = "bahan_bakar", nullable = false)
    private String bahanBakar;
}
package com.example.kendaraan.repository;

import com.example.kendaraan.model.KendaraanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface KendaraanRepository extends JpaRepository<KendaraanModel, String> {
    Optional<KendaraanModel> findByNomorRegistrasi(String nomorRegistrasi);
    boolean existsByNomorRegistrasi(String nomorRegistrasi);
    List<KendaraanModel> findByNomorRegistrasiAndNamaPemilik(String nomorRegistrasi, String namaPemilik);
    List<KendaraanModel> findByNamaPemilik(String namaPemilik);
}
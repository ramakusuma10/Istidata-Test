package com.example.kendaraan.controller;

import com.example.kendaraan.dto.KendaraanDTO;
import com.example.kendaraan.dto.ResponseTemplate;
import com.example.kendaraan.model.KendaraanModel;
import com.example.kendaraan.service.KendaraanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/kendaraan")
public class KendaraanController {


    private final KendaraanService kendaraanService;

    public KendaraanController(KendaraanService kendaraanService) {
        this.kendaraanService = kendaraanService;
    }

    @PostMapping
    public ResponseEntity<ResponseTemplate<KendaraanModel>> createKendaraan(@RequestBody KendaraanDTO kendaraanDTO) {
        try {
            KendaraanModel createdKendaraan = kendaraanService.createKendaraan(kendaraanDTO);
            ResponseTemplate<KendaraanModel> response = new ResponseTemplate<>(200, "Data Berhasil ditambahkan", null);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            ResponseTemplate<KendaraanModel> errorResponse = new ResponseTemplate<>(400, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            ResponseTemplate<KendaraanModel> errorResponse = new ResponseTemplate<>(500, "Terjadi kesalahan internal", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }


    @GetMapping("/{nomorRegistrasi}")
    public ResponseEntity<ResponseTemplate<Object>> getKendaraanByNomorRegistrasi(@PathVariable("nomorRegistrasi") String nomorRegistrasi) {
        try {
            KendaraanDTO kendaraanDTO = kendaraanService.getKendaraanByNomorRegistrasi(nomorRegistrasi);
            ResponseTemplate<Object> response = new ResponseTemplate<>(200, "Nomor Registrasi ditemukan", kendaraanDTO);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ResponseTemplate<Object> response = new ResponseTemplate<>(404, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PutMapping("/{nomorRegistrasi}")
    public ResponseEntity<ResponseTemplate<KendaraanModel>> updatekendaraan(@PathVariable String nomorRegistrasi, @RequestBody KendaraanDTO kendaraanDTO) {
        try {
            KendaraanModel updatedKendaraan = kendaraanService.updateKendaraan(nomorRegistrasi, kendaraanDTO);
            ResponseTemplate<KendaraanModel> response = new ResponseTemplate<>(200, "Data Berhasil diperbaharui", null);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            ResponseTemplate<KendaraanModel> errorResponse = new ResponseTemplate<>(400, e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (NoSuchElementException e) {
            ResponseTemplate<KendaraanModel> errorResponse = new ResponseTemplate<>(404, "Nomor Registrasi atau Kendaraan tidak ditemukan", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (Exception e) {
            ResponseTemplate<KendaraanModel> errorResponse = new ResponseTemplate<>(500, "Terjadi kesalahan internal", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @DeleteMapping("/{nomorRegistrasi}")
    public ResponseEntity<ResponseTemplate<Void>> deleteKendaraan(@PathVariable String nomorRegistrasi) {
        try {
            kendaraanService.deleteKendaraan(nomorRegistrasi);
            ResponseTemplate<Void> response = new ResponseTemplate<>(200, "Data Berhasil di Hapus", null);
            return ResponseEntity.ok(response);
        } catch (NoSuchElementException e) {
            ResponseTemplate<Void> response = new ResponseTemplate<>(404, "Nomor Registrasi tidak ditemukan", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseTemplate<List<KendaraanModel>>> getAllKendaraan() {
        List<KendaraanModel> kendaraanList = kendaraanService.getAllKendaraan();
        ResponseTemplate<List<KendaraanModel>> response = new ResponseTemplate<>(200, "Data Kendaraan ditampilkan", kendaraanList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseTemplate<List<KendaraanModel>>> searchKendaraan(
            @RequestParam(required = false) String nomorRegistrasi,
            @RequestParam(required = false) String namaPemilik) {
        try {
            List<KendaraanModel> result = kendaraanService.searchKendaraan(nomorRegistrasi, namaPemilik);
            if (result.isEmpty()) {
                ResponseTemplate<List<KendaraanModel>> response = new ResponseTemplate<>(404, "Data tidak ditemukan", null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            ResponseTemplate<List<KendaraanModel>> response = new ResponseTemplate<>(200, "Data Kendaraan ditemukan", result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseTemplate<List<KendaraanModel>> response = new ResponseTemplate<>(500, "Terjadi kesalahan internal", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
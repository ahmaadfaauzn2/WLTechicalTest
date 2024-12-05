package org.example.wl.controller;

import org.example.wl.dto.PegawaiDetailDTO;
import org.example.wl.dto.PegawaiRequestDTO;
import org.example.wl.dto.PegawaiResponseDTO;
import org.example.wl.models.Cabang;
import org.example.wl.models.Jabatan;
import org.example.wl.models.Pegawai;
import org.example.wl.repository.CabangRepository;
import org.example.wl.repository.JabatanRepository;
import org.example.wl.repository.PegawaiRepository;
import org.example.wl.request.PegawaiRequest;
import org.example.wl.service.PegawaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/pegawai")
public class PegawaiController {
    @Autowired
    private PegawaiService pegawaiService;
    @Autowired
    private CabangRepository cabangRepository;
    @Autowired
    private JabatanRepository jabatanRepository;
    @Autowired
    private PegawaiRepository pegawaiRepository;

    @GetMapping
    public List<Pegawai> getAllPegawai() {
        return pegawaiService.getAllPegawai();
    }

    @GetMapping("/{id}")
    public Pegawai getPegawaiById(@PathVariable Long id) {
        return pegawaiService.getPegawaiById(id);
    }

    // Endpoint untuk mendapatkan detail pegawai berdasarkan ID
    @GetMapping("/kontrak-habis/{id}")
    public ResponseEntity<PegawaiDetailDTO> getPegawaiDetail(@PathVariable Long id) {
        // Mendapatkan detail pegawai berdasarkan ID
        PegawaiDetailDTO pegawaiDetail = pegawaiService.getPegawaiDetailById(id);

        // Mengembalikan detail pegawai dalam response
        return ResponseEntity.ok(pegawaiDetail);
    }


    // Endpoint untuk menyimpan pegawai
    @PostMapping
    public Pegawai savePegawai(@RequestBody PegawaiRequestDTO pegawaiRequestDTO) {
        // Memanggil service untuk menyimpan pegawai
        return pegawaiService.savePegawai(pegawaiRequestDTO);
    }


    @DeleteMapping("/{id}")
    public void deletePegawaiById(@PathVariable Long id) {
        pegawaiService.deletePegawaiById(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Pegawai> updatePegawaiKontrak(
            @PathVariable Long id,
            @RequestBody PegawaiRequestDTO pegawaiRequestDTO) {

        // Validasi apakah pegawai dengan ID tersebut ada
        Pegawai existingPegawai = pegawaiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pegawai tidak ditemukan dengan ID: " + id));

        // Update tanggal mulai dan tanggal akhir kontrak
        existingPegawai.setTanggalMulaiKontrak(pegawaiRequestDTO.getTanggalMulaiKontrak());
        existingPegawai.setTanggalAkhirKontrak(pegawaiRequestDTO.getTanggalAkhirKontrak());

        // Simpan perubahan ke database
        Pegawai updatedPegawai = pegawaiRepository.save(existingPegawai);

        // Kembalikan response dengan data pegawai yang sudah diperbarui
        return ResponseEntity.ok(updatedPegawai);
    }



    @PostMapping("/upload")
    public ResponseEntity<String> uploadPegawaiFile(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty() || !file.getOriginalFilename().endsWith(".xlsx")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File harus dalam format Excel (.xlsx)");
            }

            // Process file
            pegawaiService.processPegawaiFile(file);

            return ResponseEntity.ok("File berhasil diproses dan data disimpan ke database.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Terjadi kesalahan: " + e.getMessage());
        }
    }





}
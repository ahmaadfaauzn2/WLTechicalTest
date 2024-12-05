package org.example.wl.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.wl.dto.PegawaiDetailDTO;
import org.example.wl.dto.PegawaiRequestDTO;
import org.example.wl.dto.PegawaiResponseDTO;
import org.example.wl.models.Cabang;
import org.example.wl.models.Jabatan;
import org.example.wl.models.Pegawai;
import org.example.wl.repository.CabangRepository;
import org.example.wl.repository.JabatanRepository;
import org.example.wl.repository.PegawaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PegawaiService {
    @Autowired
    private PegawaiRepository pegawaiRepository;
    @Autowired
    private CabangRepository cabangRepository;
    @Autowired
    private JabatanRepository jabatanRepository;
    @Autowired
    private CabangService cabangService;

    // Method untuk mendapatkan semua pegawai
    public List<Pegawai> getAllPegawai() {
        return pegawaiRepository.findAll();
    }
    public PegawaiService(PegawaiRepository pegawaiRepository,
                          CabangRepository cabangRepository,
                          JabatanRepository jabatanRepository) {
        this.pegawaiRepository = pegawaiRepository;
        this.cabangRepository = cabangRepository;
        this.jabatanRepository = jabatanRepository;
    }

//    public PegawaiResponseDTO updatePegawaiKontrak(Long id, PegawaiRequestDTO pegawaiRequestDTO) {
//        // Validasi apakah pegawai dengan ID tersebut ada
//        Pegawai existingPegawai = pegawaiRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Pegawai tidak ditemukan dengan ID: " + id));
//
//        // Update tanggal mulai dan akhir kontrak
//        existingPegawai.setTanggalMulaiKontrak(pegawaiRequestDTO.getTanggalMulaiKontrak());
//        existingPegawai.setTanggalAkhirKontrak(pegawaiRequestDTO.getTanggalAkhirKontrak());
//
//        // Simpan perubahan ke database
//        Pegawai updatedPegawai = pegawaiRepository.save(existingPegawai);
//
//        // Return DTO
//        return new PegawaiResponseDTO(
//                updatedPegawai.getId(),
//                updatedPegawai.getNama(),
//                updatedPegawai.getTanggalMulaiKontrak(),
//                updatedPegawai.getTanggalAkhirKontrak()
//        );
//    }
    public String processPegawaiFile(MultipartFile file) throws Exception {
        List<Pegawai> pegawaiList = new ArrayList<>();

        try (InputStream is = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(is)) {

            Sheet sheet = workbook.getSheetAt(0); // Ambil sheet pertama
            int rowCount = 0;

            for (Row row : sheet) {
                // Lewati header
                if (rowCount == 0) {
                    rowCount++;
                    continue;
                }

                Pegawai pegawai = new Pegawai();

                // Ambil nama pegawai
                pegawai.setNama(row.getCell(0).getStringCellValue());

                // Ambil nama cabang dan cari cabang di database
                String namaCabang = row.getCell(2).getStringCellValue();
                Cabang cabang = cabangRepository.findByNamaCabang(namaCabang)
                        .orElseThrow(() -> new RuntimeException("Cabang tidak ditemukan: " + namaCabang));
                pegawai.setCabang(cabang);

                // Ambil nama jabatan dan cari jabatan di database
                String namaJabatan = row.getCell(1).getStringCellValue();
                Jabatan jabatan = jabatanRepository.findByNamaJabatan(namaJabatan)
                        .orElseThrow(() -> new RuntimeException("Jabatan tidak ditemukan: " + namaJabatan));
                pegawai.setJabatan(jabatan);

                // Ambil tanggal mulai dan akhir kontrak
                Cell startDateCell = row.getCell(3);
                Cell endDateCell = row.getCell(4);

                if (startDateCell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(startDateCell)) {
                    pegawai.setTanggalMulaiKontrak(startDateCell.getLocalDateTimeCellValue().toLocalDate());
                }
                if (endDateCell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(endDateCell)) {
                    pegawai.setTanggalAkhirKontrak(endDateCell.getLocalDateTimeCellValue().toLocalDate());
                }

                pegawaiList.add(pegawai);
            }
        }

        // Simpan data ke database
        pegawaiRepository.saveAll(pegawaiList);
        return "Data pegawai berhasil diproses dan disimpan!";
    }
    // Method untuk mendapatkan pegawai berdasarkan ID
    public Pegawai getPegawaiById(Long id) {
        return pegawaiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pegawai dengan ID " + id + " tidak ditemukan"));
    }

    // Method untuk menyimpan pegawai baru
    public Pegawai savePegawai(PegawaiRequestDTO pegawaiRequestDTO) {
        // Retrieve the Cabang by ID
        Long cabangId = pegawaiRequestDTO.getNamaCabang();
        Cabang cabang = cabangRepository.findById(cabangId)
                .orElseThrow(() -> new RuntimeException("Cabang dengan ID " + cabangId + " tidak ditemukan"));

        // Retrieve the Jabatan by ID
        Long jabatanId = pegawaiRequestDTO.getNamaJabatan();
        Jabatan jabatan = jabatanRepository.findById(jabatanId)
                .orElseThrow(() -> new RuntimeException("Jabatan dengan ID " + jabatanId + " tidak ditemukan"));

        // Membuat dan menyimpan Pegawai
        Pegawai pegawai = new Pegawai();
        pegawai.setNama(pegawaiRequestDTO.getNama());
        pegawai.setCabang(cabang);
        pegawai.setJabatan(jabatan);
        pegawai.setTanggalMulaiKontrak(pegawaiRequestDTO.getTanggalMulaiKontrak());
        pegawai.setTanggalAkhirKontrak(pegawaiRequestDTO.getTanggalAkhirKontrak());

        return pegawaiRepository.save(pegawai);
    }


    public void deletePegawaiById(Long id) {
        pegawaiRepository.deleteById(id);
    }

    // Method untuk mendapatkan detail pegawai berdasarkan ID
    public PegawaiDetailDTO getPegawaiDetailById(Long id) {
        // Mengambil pegawai berdasarkan ID dengan data jabatan dan cabang
        Pegawai pegawai = pegawaiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pegawai dengan ID " + id + " tidak ditemukan"));

        // Membuat dan mengembalikan DTO dengan data pegawai, jabatan, dan cabang
        return new PegawaiDetailDTO(
                pegawai.getNama(),
                pegawai.getTanggalMulaiKontrak(),
                pegawai.getTanggalAkhirKontrak(),
                pegawai.getJabatan().getKodeJabatan(),
                pegawai.getJabatan().getNamaJabatan(),
                pegawai.getCabang().getNamaCabang(),
                pegawai.getCabang().getAlamat()
        );
    }


}

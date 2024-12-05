package org.example.wl.dto;

import java.time.LocalDate;

public class PegawaiRequestDTO {

    private String nama;

    private Long namaCabang;

    private Long namaJabatan;

    private LocalDate tanggalMulaiKontrak;

    private LocalDate tanggalAkhirKontrak;

    public PegawaiRequestDTO(String nama, Long namaCabang, Long namaJabatan, LocalDate tanggalAkhirKontrak, LocalDate tanggalMulaiKontrak) {
        this.nama = nama;
        this.namaCabang = namaCabang;
        this.namaJabatan = namaJabatan;
        this.tanggalAkhirKontrak = tanggalAkhirKontrak;
        this.tanggalMulaiKontrak = tanggalMulaiKontrak;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Long getNamaCabang() {
        return namaCabang;
    }

    public void setNamaCabang(Long namaCabang) {
        this.namaCabang = namaCabang;
    }

    public Long getNamaJabatan() {
        return namaJabatan;
    }

    public void setNamaJabatan(Long namaJabatan) {
        this.namaJabatan = namaJabatan;
    }

    public LocalDate getTanggalMulaiKontrak() {
        return tanggalMulaiKontrak;
    }

    public void setTanggalMulaiKontrak(LocalDate tanggalMulaiKontrak) {
        this.tanggalMulaiKontrak = tanggalMulaiKontrak;
    }

    public LocalDate getTanggalAkhirKontrak() {
        return tanggalAkhirKontrak;
    }

    public void setTanggalAkhirKontrak(LocalDate tanggalAkhirKontrak) {
        this.tanggalAkhirKontrak = tanggalAkhirKontrak;
    }


}

package org.example.wl.dto;

import java.time.LocalDate;

public class PegawaiDetailDTO {

    private String nama;

    private LocalDate tanggalMulaiKontrak;

    private LocalDate tanggalAkhirKontrak;

    private String kodeJabatan;

    private String namaJabatan;

    private String namaCabang;

    private String alamat;

    public PegawaiDetailDTO(String nama, LocalDate tanggalMulaiKontrak, LocalDate tanggalAkhirKontrak, String kodeJabatan, String namaJabatan, String namaCabang, String alamat) {
        this.nama = nama;
        this.tanggalMulaiKontrak = tanggalMulaiKontrak;
        this.tanggalAkhirKontrak = tanggalAkhirKontrak;
        this.kodeJabatan = kodeJabatan;
        this.namaJabatan = namaJabatan;
        this.namaCabang = namaCabang;
        this.alamat = alamat;
    }
    public LocalDate getTanggalMulaiKontrak() {
        return tanggalMulaiKontrak;
    }

    public void setTanggalMulaiKontrak(LocalDate tanggalMulaiKontrak) {
        this.tanggalMulaiKontrak = tanggalMulaiKontrak;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public LocalDate getTanggalAkhirKontrak() {
        return tanggalAkhirKontrak;
    }

    public void setTanggalAkhirKontrak(LocalDate tanggalAkhirKontrak) {
        this.tanggalAkhirKontrak = tanggalAkhirKontrak;
    }

    public String getKodeJabatan() {
        return kodeJabatan;
    }

    public void setKodeJabatan(String kodeJabatan) {
        this.kodeJabatan = kodeJabatan;
    }

    public String getNamaJabatan() {
        return namaJabatan;
    }

    public void setNamaJabatan(String namaJabatan) {
        this.namaJabatan = namaJabatan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNamaCabang() {
        return namaCabang;
    }

    public void setNamaCabang(String namaCabang) {
        this.namaCabang = namaCabang;
    }

}

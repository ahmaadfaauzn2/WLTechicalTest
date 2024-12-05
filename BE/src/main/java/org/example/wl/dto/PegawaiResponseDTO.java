package org.example.wl.dto;

import org.example.wl.models.Cabang;
import org.example.wl.models.Jabatan;

import java.time.LocalDate;

public class PegawaiResponseDTO {
    private Long id;
    private String nama;
    private Cabang cabang;
    private Jabatan jabatan;
    private LocalDate tanggalMulaiKontrak;
    private LocalDate tanggalAkhirKontrak;

    public PegawaiResponseDTO(Long id, String nama, Cabang cabang, Jabatan jabatan, LocalDate tanggalMulaiKontrak, LocalDate tanggalAkhirKontrak) {
        this.id = id;
        this.nama = nama;
        this.cabang = cabang;
        this.jabatan = jabatan;
        this.tanggalMulaiKontrak = tanggalMulaiKontrak;
        this.tanggalAkhirKontrak = tanggalAkhirKontrak;
    }

    public PegawaiResponseDTO(Long id, String nama, LocalDate tanggalMulaiKontrak, LocalDate tanggalAkhirKontrak) {
        this.id = id;
        this.nama = nama;
        this.tanggalMulaiKontrak = tanggalMulaiKontrak;
        this.tanggalAkhirKontrak = tanggalAkhirKontrak;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Cabang getCabang() {
        return cabang;
    }

    public void setCabang(Cabang cabang) {
        this.cabang = cabang;
    }

    public Jabatan getJabatan() {
        return jabatan;
    }

    public void setJabatan(Jabatan jabatan) {
        this.jabatan = jabatan;
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

    // Constructor, Getter, Setter
}

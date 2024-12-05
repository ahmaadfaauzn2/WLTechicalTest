package org.example.wl.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.wl.models.Cabang;
import org.example.wl.models.Jabatan;

import java.time.LocalDate;

public class PegawaiRequest {

    @NotNull
    @Size(min = 1, max = 50)
    private String namaPegawai;

    private Cabang cabang;

    private Jabatan jabatan;

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

    @NotNull
    private Long kodeCabang;

    @NotNull
    private Long kodeJabatan;

    @NotNull
    private LocalDate tanggalMulaiKontrak;

    @NotNull
    private LocalDate tanggalAkhirKontrak;

    public @NotNull LocalDate getTanggalAkhirKontrak() {
        return tanggalAkhirKontrak;
    }

    public void setTanggalAkhirKontrak(@NotNull LocalDate tanggalAkhirKontrak) {
        this.tanggalAkhirKontrak = tanggalAkhirKontrak;
    }

    // Getters and Setters
    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public Long getKodeCabang() {
        return kodeCabang;
    }

    public void setKodeCabang(Long kodeCabang) {
        this.kodeCabang = kodeCabang;
    }

    public Long getKodeJabatan() {
        return kodeJabatan;
    }

    public void setKodeJabatan(Long kodeJabatan) {
        this.kodeJabatan = kodeJabatan;
    }

    public LocalDate getTanggalMulaiKontrak() {
        return tanggalMulaiKontrak;
    }

    public void setTanggalMulaiKontrak(LocalDate tanggalMulaiKontrak) {
        this.tanggalMulaiKontrak = tanggalMulaiKontrak;
    }


}

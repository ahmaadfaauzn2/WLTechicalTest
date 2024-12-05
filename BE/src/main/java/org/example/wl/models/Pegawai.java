package org.example.wl.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "pegawai")
public class Pegawai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nama;

    @ManyToOne
    @JoinColumn(name = "kode_cabang",referencedColumnName = "id", nullable = false)
    private Cabang cabang;

    @ManyToOne
    @JoinColumn(name = "kode_jabatan", referencedColumnName = "id", nullable = false)
    private Jabatan jabatan;


    @Column(name = "tanggal_mulai_kontrak", nullable = false)
    private LocalDate tanggalMulaiKontrak;

    public LocalDate getTanggalAkhirKontrak() {
        return tanggalAkhirKontrak;
    }

    public void setTanggalAkhirKontrak(LocalDate tanggalAkhirKontrak) {
        this.tanggalAkhirKontrak = tanggalAkhirKontrak;
    }

    @Column(name = "tanggal_akhir_kontrak", nullable = false)
    private LocalDate tanggalAkhirKontrak;

    @Version
    private Long version;

    // Getters and Setters
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
}
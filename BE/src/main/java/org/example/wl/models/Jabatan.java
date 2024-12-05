package org.example.wl.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "jabatan")
public class Jabatan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "kodeJabatan", nullable = false, length = 10)
    private String kodeJabatan;

    @Column(name = "nama_jabatan", nullable = false, length = 100)
    private String namaJabatan;

    @Column(name = "gaji_pokok", nullable = false, precision = 10, scale = 2)
    private BigDecimal gajiPokok;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getGajiPokok() {
        return gajiPokok;
    }

    public void setGajiPokok(BigDecimal gajiPokok) {
        this.gajiPokok = gajiPokok;
    }




}

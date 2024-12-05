package org.example.wl.models;

import jakarta.persistence.*;

@Entity
@Table(name = "cabang")
public class Cabang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "kode_cabang", nullable = false, length = 10)
    private String kodeCabang;

    @Column(name = "nama_cabang", nullable = false, length = 100)
    private String namaCabang;

    @Column(nullable = false, length = 255)
    private String alamat;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKodeCabang() {
        return kodeCabang;
    }

    public void setKodeCabang(String kodeCabang) {
        this.kodeCabang = kodeCabang;
    }

    public String getNamaCabang() {
        return namaCabang;
    }

    public void setNamaCabang(String namaCabang) {
        this.namaCabang = namaCabang;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}


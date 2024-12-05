package org.example.wl.repository;

import org.example.wl.models.Pegawai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PegawaiRepository extends JpaRepository<Pegawai, Long> {

    // Query untuk mengambil pegawai berdasarkan ID, dan gabungkan dengan jabatan serta cabang
    @Query("SELECT p FROM Pegawai p JOIN FETCH p.jabatan j JOIN FETCH p.cabang c WHERE p.id = :id")
    Optional<Pegawai> findByIdWithDetails(@Param("id") Long id);

}

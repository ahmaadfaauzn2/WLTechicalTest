package org.example.wl.repository;

import org.example.wl.models.Cabang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CabangRepository extends JpaRepository<Cabang, Long> {
//    Cabang findByKodeCabang(String kodeCabang);
Optional<Cabang> findByNamaCabang(String namaCabang);
}

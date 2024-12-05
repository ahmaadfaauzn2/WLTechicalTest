package org.example.wl.repository;

import org.example.wl.models.Jabatan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JabatanRepository extends JpaRepository<Jabatan, Long> {
//    List<Jabatan> findAll(); // This method is inherited from JpaRepository
Optional<Jabatan> findByNamaJabatan(String namaJabatan);
}

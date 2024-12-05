package org.example.wl.service;

import org.example.wl.models.Cabang;
import org.example.wl.repository.CabangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CabangService {
    @Autowired
    private CabangRepository cabangRepository;

    public List<Cabang> getAllCabang() {
        return cabangRepository.findAll();
    }
    public Cabang getCabangById(Long id) {
        return cabangRepository.findById(id).orElse(null);
    }
    public Cabang saveCabang(Cabang cabang) {
        return cabangRepository.save(cabang);
    }
    public void deleteCabangById(Long id) {
        cabangRepository.deleteById(id);
    }
//    public Long findIdByName(String namaCabang) {
//        Cabang cabang = cabangRepository.findByNama(namaCabang)
//                .orElseThrow(() -> new RuntimeException("Cabang dengan nama '" + namaCabang + "' tidak ditemukan!"));
//        return cabang.getId();
//    }
}

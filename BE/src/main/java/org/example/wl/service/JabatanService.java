package org.example.wl.service;

import org.example.wl.models.Jabatan;
import org.example.wl.repository.JabatanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JabatanService {
    @Autowired
    private JabatanRepository jabatanRepository;

    public List<Jabatan> getAllJabatan() {
        return jabatanRepository.findAll();
    }

    public Jabatan getJabatanById(Long id) {
        return jabatanRepository.findById(id).orElse(null);
    }

    public Jabatan saveJabatan(Jabatan jabatan) {
        return jabatanRepository.save(jabatan);
    }

    public void deleteJabatanById(Long id) {
        jabatanRepository.deleteById(id);
    }
}

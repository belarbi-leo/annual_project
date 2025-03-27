package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.modele.Materiels;
import com.pa2aresgi.pa2a.repository.MaterielsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MaterielsServiceImpl implements MaterielsService {
    private MaterielsRepository materielsRepository;

    @Override
    public Materiels create(Materiels materiel) {
        return materielsRepository.save(materiel);
    }

    @Override
    public List<Materiels> readAll() {
        return materielsRepository.findAll();
    }

    @Override
    public Materiels findById(Integer id) {
        if (materielsRepository.findById(id).isPresent()){
            return materielsRepository.findById(id).get();
        } else {
            throw new RuntimeException("Materiel not found ! ");
        }
    }

    @Override
    public Materiels update(Integer id, Materiels materiel) {    //Pas correct pour nfc, rien à modifier
        return materielsRepository.findById(id).map(mat -> {
            mat.setId_svc(materiel.getId_svc());
            mat.setName_mat(materiel.getName_mat());
            mat.setDescription_mat(materiel.getDescription_mat());
            return materielsRepository.save(mat);
        }).orElseThrow(() -> new RuntimeException("Materiel not found !"));
    }

    @Override
    public String deleteById(Integer id) {
        materielsRepository.deleteById(id);
        return "Materiel deleted !";
    }
}

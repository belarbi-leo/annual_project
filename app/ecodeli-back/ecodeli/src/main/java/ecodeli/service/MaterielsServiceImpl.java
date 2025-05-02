package ecodeli.service;

import ecodeli.modele.Materiels;
import ecodeli.repository.MaterielsRepository;
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
    public List<Materiels> readAllOrderById() {
        return materielsRepository.findAllOrderById_mat();
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
            mat.setSvc(materiel.getSvc());
            mat.setNameMateriel(materiel.getNameMateriel());
            mat.setDescriptionMateriel(materiel.getDescriptionMateriel());
            return materielsRepository.save(mat);
        }).orElseThrow(() -> new RuntimeException("Materiel not found !"));
    }

    @Override
    public String deleteById(Integer id) {
        materielsRepository.deleteById(id);
        return "Materiel deleted !";
    }
}

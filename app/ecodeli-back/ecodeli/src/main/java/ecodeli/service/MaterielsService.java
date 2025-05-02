package ecodeli.service;

import ecodeli.modele.Materiels;

import java.util.List;

public interface MaterielsService {
    Materiels create(Materiels materiel);

    List<Materiels> readAll();

    List<Materiels> readAllOrderById();

    Materiels findById(Integer id);

    Materiels update(Integer id, Materiels materiel);

    String deleteById(Integer id);
}

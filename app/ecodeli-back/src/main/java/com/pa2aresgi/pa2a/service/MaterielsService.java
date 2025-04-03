package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.modele.Materiels;

import java.util.List;

public interface MaterielsService {
    Materiels create(Materiels materiel);

    List<Materiels> readAll();

    List<Materiels> readAllOrderById();

    Materiels findById(Integer id);

    Materiels update(Integer id, Materiels materiel);

    String deleteById(Integer id);
}

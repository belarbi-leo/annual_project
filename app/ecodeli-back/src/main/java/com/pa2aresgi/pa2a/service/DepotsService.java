package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.modele.Depots;

import java.util.List;

public interface DepotsService {
    Depots create(Depots depot);

    List<Depots> readAll();

    List<Depots> readAllOrderById();

    Depots findById(Integer id);

    Depots update(Integer id, Depots depot);

    String deleteById(Integer id);
}

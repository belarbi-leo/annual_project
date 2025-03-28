package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.modele.Disputes;

import java.util.List;

public interface DisputesService {
    Disputes create(Disputes dispute);

    List<Disputes> readAll();
    Disputes findById(Integer id);

    Disputes update(Integer id, Disputes dispute);

    String deleteById(Integer id);
}

package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.modele.Routes;

import java.util.List;

public interface RoutesService {
    Routes create(Routes route);

    List<Routes> readAll();
    Routes findById(Integer id);

    Routes update(Integer id, Routes route);

    String deleteById(Integer id);
}

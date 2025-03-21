package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.modele.Packages;

import java.util.List;

public interface PackagesService {
    Packages create(Packages pack);

    List<Packages> readAll();
    Packages findById(Integer id);

    Packages update(Integer id, Packages pack);

    String deleteById(Integer id);
}

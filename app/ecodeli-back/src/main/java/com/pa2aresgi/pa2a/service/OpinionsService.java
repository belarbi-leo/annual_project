package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.modele.Opinions;

import java.util.List;

public interface OpinionsService {
    Opinions create(Opinions opinion);

    List<Opinions> readAll();

    List<Opinions> readAllOrderById();

    Opinions findById(Integer id);

    Opinions update(Integer id, Opinions opinion);

    String deleteById(Integer id);
}

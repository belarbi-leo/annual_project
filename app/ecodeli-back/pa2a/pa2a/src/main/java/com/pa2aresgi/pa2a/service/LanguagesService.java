package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.modele.Languages;

import java.util.List;

public interface LanguagesService {

    Languages create(Languages language);

    List<Languages> readAll();
    Languages findById(Integer id);

    Languages update(Integer id, Languages language);

    String deleteById(Integer id);
}

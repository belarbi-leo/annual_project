package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.modele.Authorizations;

import java.util.List;

public interface AuthorizationsService {
    Authorizations create(Authorizations authorization);

    List<Authorizations> readAll();
    Authorizations findById(Integer id);

    Authorizations update(Integer id, Authorizations authorization);

    String deleteById(Integer id);
}

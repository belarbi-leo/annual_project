package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.enumeratation.AuthorizationSvcEnum;
import com.pa2aresgi.pa2a.modele.Services;

import java.util.List;
import java.util.Set;

public interface ServicesService {
    Services create(Services service);

    List<Services> readAll();

    List<Services> readAllOrderById();

    List<Services> readAllByAuth(AuthorizationSvcEnum auth);

    List<Services> readAllByAuthIn(Set<AuthorizationSvcEnum> auth);

    public List<Services> readAllByAuthOrderById(AuthorizationSvcEnum auths);

    Services findById(Integer id);

    Services update(Integer id, Services service);

    String deleteById(Integer id);
}
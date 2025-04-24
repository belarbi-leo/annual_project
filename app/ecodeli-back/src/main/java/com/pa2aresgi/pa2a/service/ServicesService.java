package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.enumeratation.AuthorizationSvcEnum;
import com.pa2aresgi.pa2a.modele.Services;

import java.util.List;

public interface ServicesService {
    Services create(Services service);

    List<Services> readAll();

    List<Services> readAllOrderById();

    List<Services> readAllByAuth(AuthorizationSvcEnum auth);

    public List<Services> readAllByAuthOrderById(AuthorizationSvcEnum auth);

    Services findById(Integer id);

    Services update(Integer id, Services service);

    String deleteById(Integer id);
}

package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.modele.Requests_svc;

import java.util.List;

public interface Requests_svcService {
    Requests_svc create(Requests_svc request_svc);

    List<Requests_svc> readAll();

    List<Requests_svc> readAllOrderById();

    Requests_svc findById(Integer id);

    Requests_svc update(Integer id, Requests_svc request_svc);

    String deleteById(Integer id);
}

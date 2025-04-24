package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.modele.ServicesDocs;

import java.util.List;

public interface ServicesDocsService {
    ServicesDocs create(ServicesDocs serviceDoc);

    List<ServicesDocs> readAll();

    List<ServicesDocs> readAllOrderById();

    ServicesDocs findById(Integer id);

    ServicesDocs update(Integer id, ServicesDocs serviceDoc);

    String deleteById(Integer id);
}

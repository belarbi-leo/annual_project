package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.modele.RequestsServices;

import java.util.List;

public interface RequestsServicesService {
    RequestsServices create(RequestsServices requestSvc);

    List<RequestsServices> readAll();

    List<RequestsServices> readAllOrderById();

    RequestsServices findById(Integer id);

    RequestsServices update(Integer id, RequestsServices requestSvc);

    String deleteById(Integer id);
}

package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.modele.RequestsDocs;

import java.util.List;

public interface RequestsDocsService {
    RequestsDocs create(RequestsDocs requestDoc);

    List<RequestsDocs> readAll();

    List<RequestsDocs> readAllOrderById();

    RequestsDocs findById(Integer id);

    RequestsDocs update(Integer id, RequestsDocs requestDoc);

    String deleteById(Integer id);
}

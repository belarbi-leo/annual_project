package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.modele.Services_docs;

import java.util.List;

public interface Services_docsService {
    Services_docs create(Services_docs service_doc);

    List<Services_docs> readAll();

    List<Services_docs> readAllOrderById();

    Services_docs findById(Integer id);

    Services_docs update(Integer id, Services_docs service_doc);

    String deleteById(Integer id);
}

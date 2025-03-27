package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.modele.Requests_docs;

import java.util.List;

public interface Requests_docsService {
    Requests_docs create(Requests_docs request_doc);

    List<Requests_docs> readAll();
    Requests_docs findById(Integer id);

    Requests_docs update(Integer id, Requests_docs request_doc);

    String deleteById(Integer id);
}

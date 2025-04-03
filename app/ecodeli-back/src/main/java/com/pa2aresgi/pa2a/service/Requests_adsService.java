package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.modele.Requests_ads;

import java.util.List;

public interface Requests_adsService {
    Requests_ads create(Requests_ads request_ad);

    List<Requests_ads> readAll();

    List<Requests_ads> readAllOrderById();

    Requests_ads findById(Integer id);

    Requests_ads update(Integer id, Requests_ads request_ad);

    String deleteById(Integer id);
}

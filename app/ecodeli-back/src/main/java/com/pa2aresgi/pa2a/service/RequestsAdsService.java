package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.modele.RequestsAds;

import java.util.List;

public interface RequestsAdsService {
    RequestsAds create(RequestsAds requestAd);

    List<RequestsAds> readAll();

    List<RequestsAds> readAllOrderById();

    RequestsAds findById(Integer id);

    RequestsAds update(Integer id, RequestsAds requestAd);

    String deleteById(Integer id);
}

package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.modele.*;
import com.pa2aresgi.pa2a.repository.AdsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class AdsServiceImpl implements AdsService {

    private AdsRepository adsRepository;

    @Override
    public Ads create(Ads ad) {
        return adsRepository.save(ad);
    }

    @Override
    public List<Ads> readAll() {
        return adsRepository.findAll();
    }

    @Override
    public List<Ads> readAllOrderById() {
        return adsRepository.findAllOrderById_ad();
    }

    @Override
    public Ads findById(Integer id) {
        if (adsRepository.findById(id).isPresent()){
            return adsRepository.findById(id).get();
        } else {
            throw new RuntimeException("Ad not found ! ");
        }
    }

    @Override
    public Ads update(Integer id, Ads ad) {
        return adsRepository.findById(id).map(a -> {
            a.setId_user_creator(ad.getId_user_creator());
            a.setId_user_accept(ad.getId_user_accept());
            a.setId_svc(ad.getId_svc());
            //a.setId_sub(ad.getId_sub());
            a.setStatus_ad(ad.getStatus_ad());
            a.setDate_creation_ad(ad.getDate_creation_ad());
            a.setDate_accept_ad(ad.getDate_accept_ad());
            a.setDate_start_ad(ad.getDate_start_ad());
            //a.setStreet_start_ad(ad.getStreet_start_ad());
            a.setLocation_start(ad.getLocation_start());
            a.setSuite_start(ad.getSuite_start());
            a.setLocality_start(ad.getLocality_start());
            a.setState_start(ad.getState_start());
            a.setPostal_code_start(ad.getPostal_code_start());
            a.setCountry_start(ad.getCountry_start());
            a.setDate_end_ad(ad.getDate_end_ad());
            //a.setStreet_end_ad(ad.getStreet_end_ad());
            a.setLocation_end(ad.getLocation_end());
            a.setSuite_end(ad.getSuite_end());
            a.setLocality_end(ad.getLocality_end());
            a.setState_end(ad.getState_end());
            a.setPostal_code_end(ad.getPostal_code_end());
            a.setCountry_end(ad.getCountry_end());
            a.setDescription_ad(ad.getDescription_ad());
            a.setPrice_ad(ad.getPrice_ad());
            a.setPhoto_ad(ad.getPhoto_ad());/*
            a.setRequests_ads_list(ad.getRequests_ads_list());
            a.setPackages_list(ad.getPackages_list());
            a.setOpinions_list(ad.getOpinions_list());
            a.setPayments_list(ad.getPayments_list());
            a.setDisputes_list(ad.getDisputes_list());
            a.setRoutes_set(ad.getRoutes_set());*/
            return adsRepository.save(a);
        }).orElseThrow(() -> new RuntimeException("Ad not found !"));
    }

    @Override
    public String deleteById(Integer id) {
        return "Ad deleted!";
    }
}
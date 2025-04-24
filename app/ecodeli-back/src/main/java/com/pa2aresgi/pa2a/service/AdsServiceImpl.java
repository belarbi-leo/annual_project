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
            a.setUserCreator(ad.getUserCreator());
            a.setUserAccept(ad.getUserAccept());
            a.setSvc(ad.getSvc());
            //a.setId_sub(ad.getId_sub());
            a.setStatusAd(ad.getStatusAd());
            a.setDateCreationAd(ad.getDateCreationAd());
            a.setDateAcceptAd(ad.getDateAcceptAd());
            a.setDateStartAd(ad.getDateStartAd());
            //a.setStreet_start_ad(ad.getStreet_start_ad());
            a.setLocationStart(ad.getLocationStart());
            a.setSuiteStart(ad.getSuiteStart());
            a.setLocalityStart(ad.getLocalityStart());
            a.setStateStart(ad.getStateStart());
            a.setPostalCodeStart(ad.getPostalCodeStart());
            a.setCountryStart(ad.getCountryStart());
            a.setLatitudeStart(ad.getLatitudeStart());
            a.setLongitudeStart(ad.getLongitudeStart());
            a.setDateEndAd(ad.getDateEndAd());
            //a.setStreet_end_ad(ad.getStreet_end_ad());
            a.setLocationEnd(ad.getLocationEnd());
            a.setSuiteEnd(ad.getSuiteEnd());
            a.setLocalityEnd(ad.getLocalityEnd());
            a.setStateEnd(ad.getStateEnd());
            a.setPostalCodeEnd(ad.getPostalCodeEnd());
            a.setCountryEnd(ad.getCountryEnd());
            a.setLatitudeEnd(ad.getLatitudeEnd());
            a.setLongitudeEnd(ad.getLongitudeEnd());
            a.setDescriptionAd(ad.getDescriptionAd());
            a.setPriceAd(ad.getPriceAd());
            a.setPhotoAd(ad.getPhotoAd());/*
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
package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.modele.Requests_ads;
import com.pa2aresgi.pa2a.repository.Requests_adsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class Requests_adsServiceImpl implements Requests_adsService {
    private Requests_adsRepository requests_adsRepository;

    @Override
    public Requests_ads create(Requests_ads request_ad) {
        return requests_adsRepository.save(request_ad);
    }

    @Override
    public List<Requests_ads> readAll() {
        return requests_adsRepository.findAll();
    }

    @Override
    public List<Requests_ads> readAllOrderById() {
        return requests_adsRepository.findAllOrderById_req_annonce();
    }

    @Override
    public Requests_ads findById(Integer id) {
        if (requests_adsRepository.findById(id).isPresent()){
            return requests_adsRepository.findById(id).get();
        } else {
            throw new RuntimeException("Request_ad not found ! ");
        }
    }

    @Override
    public Requests_ads update(Integer id, Requests_ads request_ad) {
        return requests_adsRepository.findById(id).map(rq_ad -> {
            rq_ad.setId_user(request_ad.getId_user());
            rq_ad.setId_ad(request_ad.getId_ad());
            rq_ad.setStatus_req_annonce(request_ad.getStatus_req_annonce());
            rq_ad.setDate_creation_req_annonce(request_ad.getDate_creation_req_annonce());
            rq_ad.setDate_accept_req_annonce(request_ad.getDate_accept_req_annonce());
            rq_ad.setDate_start_req_annonce(request_ad.getDate_start_req_annonce());
            rq_ad.setStreet_start_req_annonce(request_ad.getStreet_start_req_annonce());
            rq_ad.setPostal_code_start_req_annonce(request_ad.getPostal_code_start_req_annonce());
            rq_ad.setCountry_start_req_annonce(request_ad.getCountry_start_req_annonce());
            rq_ad.setDate_end_req_annonce(request_ad.getDate_end_req_annonce());
            rq_ad.setStreet_end_req_annonce(request_ad.getStreet_end_req_annonce());
            rq_ad.setPostal_code_end_req_annonce(request_ad.getPostal_code_end_req_annonce());
            rq_ad.setCountry_end_req_annonce(request_ad.getCountry_end_req_annonce());
            rq_ad.setMessage_req_annonce(request_ad.getMessage_req_annonce());
            rq_ad.setPrice_req_annonce(request_ad.getPrice_req_annonce());
            return requests_adsRepository.save(rq_ad);
        }).orElseThrow(() -> new RuntimeException("Request_ad not found !"));
    }

    @Override
    public String deleteById(Integer id) {
        requests_adsRepository.deleteById(id);
        return "Request_ad deleted !";
    }
}
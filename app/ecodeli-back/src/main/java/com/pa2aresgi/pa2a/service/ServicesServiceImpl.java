package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.modele.*;
import com.pa2aresgi.pa2a.repository.ServicesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServicesServiceImpl implements ServicesService {
    private ServicesRepository servicesRepository;

    @Override
    public Services create(Services service) {
        return servicesRepository.save(service);
    }

    @Override
    public List<Services> readAll() {
        return servicesRepository.findAll();
    }

    @Override
    public Services findById(Integer id) {
        if (servicesRepository.findById(id).isPresent()){
            return servicesRepository.findById(id).get();
        } else {
            throw new RuntimeException("Service not found ! ");
        }
    }

    @Override
    public Services update(Integer id, Services service) {
        return servicesRepository.findById(id).map(svc -> {
            svc.setId_admin_creator(service.getId_admin_creator());
            svc.setDate_creation_svc(service.getDate_creation_svc());
            svc.setName_svc(service.getName_svc());
            svc.setCategory(service.getCategory());/*
            svc.setServices_docs_list(service.getServices_docs_list());
            svc.setMateriels_list(service.getMateriels_list());
            svc.setAuthorizations_list(service.getAuthorizations_list());
            svc.setRequests_svc_list(service.getRequests_svc_list());
            svc.setAds_list(service.getAds_list());*/
            return servicesRepository.save(svc);
        }).orElseThrow(() -> new RuntimeException("Service not found !"));
    }

    @Override
    public String deleteById(Integer id) {
        servicesRepository.deleteById(id);
        return "Service deleted !";
    }
}

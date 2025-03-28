package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.modele.Routes;
import com.pa2aresgi.pa2a.repository.RoutesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoutesServiceImpl implements RoutesService {
    private RoutesRepository routesRepository;

    @Override
    public Routes create(Routes route) {
        return routesRepository.save(route);
    }

    @Override
    public List<Routes> readAll() {
        return routesRepository.findAll();
    }

    @Override
    public Routes findById(Integer id) {
        if (routesRepository.findById(id).isPresent()){
            return routesRepository.findById(id).get();
        } else {
            throw new RuntimeException("Route not found ! ");
        }
    }

    @Override
    public Routes update(Integer id, Routes route) {
        return routesRepository.findById(id).map(rt -> {
            rt.setId_user(route.getId_user());
            rt.setDate_creation_route(route.getDate_creation_route());
            rt.setDate_start_route(route.getDate_start_route());
            rt.setStreet_start_route(route.getStreet_start_route());
            rt.setPostal_code_start_route(route.getPostal_code_start_route());
            rt.setCountry_start_route(route.getCountry_start_route());
            rt.setDate_end_route(route.getDate_end_route());
            rt.setStreet_end_route(route.getStreet_end_route());
            rt.setPostal_code_end_route(route.getPostal_code_end_route());
            rt.setCountry_end_route(route.getCountry_end_route());
            rt.setDescription_route(route.getDescription_route());/*
            rt.setAds_set(route.getAds_set());*/
            return routesRepository.save(rt);
        }).orElseThrow(() -> new RuntimeException("Route not found !"));
    }

    @Override
    public String deleteById(Integer id) {
        routesRepository.deleteById(id);
        return "Route deleted !";
    }
}

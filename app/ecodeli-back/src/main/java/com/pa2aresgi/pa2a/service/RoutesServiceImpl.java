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
    public List<Routes> readAllOrderById() {
        return routesRepository.findAllOrderById_route();
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
            rt.setUser(route.getUser());
            rt.setDateCreationRoute(route.getDateCreationRoute());
            rt.setDateStartRoute(route.getDateStartRoute());
            //rt.setStreet_start_route(route.getStreet_start_route());
            rt.setLocationStartRoute(route.getLocationStartRoute());
            rt.setSuiteStartRoute(route.getSuiteStartRoute());
            rt.setLocalityStartRoute(route.getLocalityStartRoute());
            rt.setStateStartRoute(route.getStateStartRoute());
            rt.setPostalCodeStartRoute(route.getPostalCodeStartRoute());
            rt.setCountryStartRoute(route.getCountryStartRoute());
            rt.setLatitudeStartRoute(route.getLatitudeStartRoute());
            rt.setLongitudeStartRoute(route.getLongitudeStartRoute());
            rt.setDateEndRoute(route.getDateEndRoute());
            //rt.setStreet_end_route(route.getStreet_end_route());
            rt.setLocationEndRoute(route.getLocationEndRoute());
            rt.setSuiteEndRoute(route.getSuiteEndRoute());
            rt.setLocalityEndRoute(route.getLocalityEndRoute());
            rt.setStateEndRoute(route.getStateEndRoute());
            rt.setPostalCodeEndRoute(route.getPostalCodeEndRoute());
            rt.setCountryEndRoute(route.getCountryEndRoute());
            rt.setLatitudeEndRoute(route.getLatitudeEndRoute());
            rt.setLongitudeEndRoute(route.getLongitudeEndRoute());
            rt.setDescriptionRoute(route.getDescriptionRoute());
            rt.setStepRoute(route.getStepRoute());
            //rt.setAds_set(route.getAds_set());
            return routesRepository.save(rt);
        }).orElseThrow(() -> new RuntimeException("Route not found !"));
    }

    @Override
    public String deleteById(Integer id) {
        routesRepository.deleteById(id);
        return "Route deleted !";
    }
}

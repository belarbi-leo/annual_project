package com.pa2aresgi.pa2a.controller;

import com.pa2aresgi.pa2a.enumeratation.AuthorizationSvcEnum;
import com.pa2aresgi.pa2a.modele.Services;
import com.pa2aresgi.pa2a.service.ServicesService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Profile({"dev", "prod"})
@RestController
@RequestMapping("/services")
@AllArgsConstructor
public class ServicesController {

    private final ServicesService servicesService;

    @PostMapping("/create")
    public Services create(@RequestBody Services service){
        return servicesService.create(service);
    }

    @GetMapping("/read")
    public List<Services> readAll(@RequestParam(name = "auth", required = false) Set<AuthorizationSvcEnum> auth){
        if (auth!=null && !auth.isEmpty()) return servicesService.readAllByAuthIn(auth);
        return servicesService.readAllOrderById();
        //return servicesService.readAll();
    }

    @GetMapping("/read/{id}")
    public Services findById(@PathVariable Integer id){
        return servicesService.findById(id);
    }


    @PutMapping("/update/{id}")
    public Services update(@PathVariable Integer id, @RequestBody Services service) {
        return servicesService.update(id, service);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return servicesService.deleteById(id);
    }
}
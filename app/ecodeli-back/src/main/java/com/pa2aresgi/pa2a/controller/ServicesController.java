package com.pa2aresgi.pa2a.controller;

import com.pa2aresgi.pa2a.modele.Services;
import com.pa2aresgi.pa2a.service.ServicesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Services> readAll(){
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

package com.pa2aresgi.pa2a.controller;

import com.pa2aresgi.pa2a.modele.Routes;
import com.pa2aresgi.pa2a.service.RoutesService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Profile({"dev", "prod"})
@RestController
@RequestMapping("/routes")
@AllArgsConstructor
public class RoutesController {

    private final RoutesService routesService;

    @PostMapping("/create")
    public Routes create(@RequestBody Routes route){
        return routesService.create(route);
    }

    @GetMapping("/read")
    public List<Routes> readAll(){
        return routesService.readAllOrderById();
        //return routesService.readAll();
    }

    @GetMapping("/read/{id}")
    public Routes findById(@PathVariable Integer id){
        return routesService.findById(id);
    }


    @PutMapping("/update/{id}")
    public Routes update(@PathVariable Integer id, @RequestBody Routes route) {
        return routesService.update(id, route);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return routesService.deleteById(id);
    }
}


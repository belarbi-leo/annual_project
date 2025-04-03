package com.pa2aresgi.pa2a.controller;

import com.pa2aresgi.pa2a.modele.Materiels;
import com.pa2aresgi.pa2a.service.MaterielsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materiels")
@AllArgsConstructor
public class MaterielsController {

    private final MaterielsService materielsService;

    @PostMapping("/create")
    public Materiels create(@RequestBody Materiels materiel){
        return materielsService.create(materiel);
    }

    @GetMapping("/read")
    public List<Materiels> readAll(){
        return materielsService.readAllOrderById();
        //return materielsService.readAll();
    }

    @GetMapping("/read/{id}")
    public Materiels findById(@PathVariable Integer id){
        return materielsService.findById(id);
    }


    @PutMapping("/update/{id}")
    public Materiels update(@PathVariable Integer id, @RequestBody Materiels materiel) {
        return materielsService.update(id, materiel);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return materielsService.deleteById(id);
    }
}
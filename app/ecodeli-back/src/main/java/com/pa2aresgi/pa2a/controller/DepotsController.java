package com.pa2aresgi.pa2a.controller;

import com.pa2aresgi.pa2a.modele.Depots;
import com.pa2aresgi.pa2a.service.DepotsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Profile("dev")
@RestController
@RequestMapping("/depots")
@AllArgsConstructor
public class DepotsController {

    private final DepotsService depotsService;

    @PostMapping("/create")
    public Depots create(@RequestBody Depots depot){
        return depotsService.create(depot);
    }

    @GetMapping("/read")
    public List<Depots> readAll(){
        return depotsService.readAllOrderById();
        //return depotsService.readAll();
    }

    @GetMapping("/read/{id}")
    public Depots findById(@PathVariable Integer id){
        return depotsService.findById(id);
    }


    @PutMapping("/update/{id}")
    public Depots update(@PathVariable Integer id, @RequestBody Depots depot) {
        return depotsService.update(id, depot);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return depotsService.deleteById(id);
    }
}

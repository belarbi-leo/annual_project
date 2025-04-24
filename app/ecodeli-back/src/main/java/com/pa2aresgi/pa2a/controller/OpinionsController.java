package com.pa2aresgi.pa2a.controller;

import com.pa2aresgi.pa2a.modele.Opinions;
import com.pa2aresgi.pa2a.service.OpinionsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Profile("dev")
@RestController
@RequestMapping("/opinions")
@AllArgsConstructor
public class OpinionsController {

    private final OpinionsService opinionsService;

    @PostMapping("/create")
    public Opinions create(@RequestBody Opinions opinion){
        return opinionsService.create(opinion);
    }

    @GetMapping("/read")
    public List<Opinions> readAll(){
        return opinionsService.readAllOrderById();
        //return opinionsService.readAll();
    }

    @GetMapping("/read/{id}")
    public Opinions findById(@PathVariable Integer id){
        return opinionsService.findById(id);
    }


    @PutMapping("/update/{id}")
    public Opinions update(@PathVariable Integer id, @RequestBody Opinions opinion) {
        return opinionsService.update(id, opinion);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return opinionsService.deleteById(id);
    }
}

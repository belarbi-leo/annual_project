package com.pa2aresgi.pa2a.controller;

import com.pa2aresgi.pa2a.modele.Packages;
import com.pa2aresgi.pa2a.service.PackagesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/packages")
@AllArgsConstructor
public class PackagesController {

    private final PackagesService packagesService;

    @PostMapping("/create")
    public Packages create(@RequestBody Packages pack){
        return packagesService.create(pack);
    }

    @GetMapping("/read")
    public List<Packages> readAll(){
        return packagesService.readAll();
    }

    @GetMapping("/read/{id}")
    public Packages findById(@PathVariable Integer id){
        return packagesService.findById(id);
    }


    @PutMapping("/update/{id}")
    public Packages update(@PathVariable Integer id, @RequestBody Packages pack) {
        return packagesService.update(id, pack);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return packagesService.deleteById(id);
    }
}

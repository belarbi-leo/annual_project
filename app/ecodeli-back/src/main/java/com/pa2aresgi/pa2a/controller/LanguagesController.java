package com.pa2aresgi.pa2a.controller;

import com.pa2aresgi.pa2a.modele.Languages;
import com.pa2aresgi.pa2a.service.LanguagesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/languages")
@AllArgsConstructor
public class LanguagesController {

    private final LanguagesService languagesService;

    @PostMapping("/create")
    public Languages create(@RequestBody Languages language){
        return languagesService.create(language);
    }

    @GetMapping("/read")
    public List<Languages> readAll(){
        return languagesService.readAllOrderById();
        //return languagesService.readAll();
    }

    @GetMapping("/read/{id}")
    public Languages findById(@PathVariable Integer id){
        return languagesService.findById(id);
    }


    @PutMapping("/update/{id}")
    public Languages update(@PathVariable Integer id, @RequestBody Languages language) {
        return languagesService.update(id, language);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return languagesService.deleteById(id);
    }
}

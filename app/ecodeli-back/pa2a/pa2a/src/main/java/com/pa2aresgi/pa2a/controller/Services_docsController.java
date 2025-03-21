package com.pa2aresgi.pa2a.controller;

import com.pa2aresgi.pa2a.modele.Services_docs;
import com.pa2aresgi.pa2a.service.Services_docsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services_docs")
@AllArgsConstructor
public class Services_docsController {

    private final Services_docsService services_docsService;

    @PostMapping("/create")
    public Services_docs create(@RequestBody Services_docs service_doc){
        return services_docsService.create(service_doc);
    }

    @GetMapping("/read")
    public List<Services_docs> readAll(){
        return services_docsService.readAll();
    }

    @GetMapping("/read/{id}")
    public Services_docs findById(@PathVariable Integer id){
        return services_docsService.findById(id);
    }


    @PutMapping("/update/{id}")
    public Services_docs update(@PathVariable Integer id, @RequestBody Services_docs service_doc) {
        return services_docsService.update(id, service_doc);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return services_docsService.deleteById(id);
    }
}


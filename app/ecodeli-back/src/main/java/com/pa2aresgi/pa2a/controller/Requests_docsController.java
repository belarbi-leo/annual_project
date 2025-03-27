package com.pa2aresgi.pa2a.controller;

import com.pa2aresgi.pa2a.modele.Requests_docs;
import com.pa2aresgi.pa2a.service.Requests_docsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests_docs")
@AllArgsConstructor
public class Requests_docsController {

    private final Requests_docsService requests_docsService;

    @PostMapping("/create")
    public Requests_docs create(@RequestBody Requests_docs request_doc){
        return requests_docsService.create(request_doc);
    }

    @GetMapping("/read")
    public List<Requests_docs> readAll(){
        return requests_docsService.readAll();
    }

    @GetMapping("/read/{id}")
    public Requests_docs findById(@PathVariable Integer id){
        return requests_docsService.findById(id);
    }


    @PutMapping("/update/{id}")
    public Requests_docs update(@PathVariable Integer id, @RequestBody Requests_docs request_doc) {
        return requests_docsService.update(id, request_doc);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return requests_docsService.deleteById(id);
    }
}

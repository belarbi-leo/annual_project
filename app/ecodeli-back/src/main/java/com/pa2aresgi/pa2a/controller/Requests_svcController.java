package com.pa2aresgi.pa2a.controller;

import com.pa2aresgi.pa2a.modele.Requests_svc;
import com.pa2aresgi.pa2a.service.Requests_svcService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests_svc")
@AllArgsConstructor
public class Requests_svcController {

    private final Requests_svcService requests_svcService;

    @PostMapping("/create")
    public Requests_svc create(@RequestBody Requests_svc request_svc){
        return requests_svcService.create(request_svc);
    }

    @GetMapping("/read")
    public List<Requests_svc> readAll(){
        return requests_svcService.readAllOrderById();
        //return requests_svcService.readAll();
    }

    @GetMapping("/read/{id}")
    public Requests_svc findById(@PathVariable Integer id){
        return requests_svcService.findById(id);
    }


    @PutMapping("/update/{id}")
    public Requests_svc update(@PathVariable Integer id, @RequestBody Requests_svc request_svc) {
        return requests_svcService.update(id, request_svc);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return requests_svcService.deleteById(id);
    }
}

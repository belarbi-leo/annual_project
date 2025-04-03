package com.pa2aresgi.pa2a.controller;

import com.pa2aresgi.pa2a.modele.Requests_ads;
import com.pa2aresgi.pa2a.service.Requests_adsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests_ads")
@AllArgsConstructor
public class Requests_adsController {

    private final Requests_adsService requests_adsService;

    @PostMapping("/create")
    public Requests_ads create(@RequestBody Requests_ads request_ad){
        return requests_adsService.create(request_ad);
    }

    @GetMapping("/read")
    public List<Requests_ads> readAll(){
        return requests_adsService.readAllOrderById();
        //return requests_adsService.readAll();
    }

    @GetMapping("/read/{id}")
    public Requests_ads findById(@PathVariable Integer id){
        return requests_adsService.findById(id);
    }


    @PutMapping("/update/{id}")
    public Requests_ads update(@PathVariable Integer id, @RequestBody Requests_ads request_ad) {
        return requests_adsService.update(id, request_ad);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return requests_adsService.deleteById(id);
    }
}

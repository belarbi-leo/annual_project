package com.pa2aresgi.pa2a.controller;

import com.pa2aresgi.pa2a.modele.RequestsAds;
import com.pa2aresgi.pa2a.service.RequestsAdsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Profile("dev")
@RestController
@RequestMapping("/requestsAds")
@AllArgsConstructor
public class RequestsAdsController {

    private final RequestsAdsService requestsAdsService;

    @PostMapping("/create")
    public RequestsAds create(@RequestBody RequestsAds requestAd){
        return requestsAdsService.create(requestAd);
    }

    @GetMapping("/read")
    public List<RequestsAds> readAll(){
        return requestsAdsService.readAllOrderById();
        //return requests_adsService.readAll();
    }

    @GetMapping("/read/{id}")
    public RequestsAds findById(@PathVariable Integer id){
        return requestsAdsService.findById(id);
    }


    @PutMapping("/update/{id}")
    public RequestsAds update(@PathVariable Integer id, @RequestBody RequestsAds requestAd) {
        return requestsAdsService.update(id, requestAd);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return requestsAdsService.deleteById(id);
    }
}

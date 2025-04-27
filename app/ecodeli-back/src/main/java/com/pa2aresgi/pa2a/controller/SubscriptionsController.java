package com.pa2aresgi.pa2a.controller;

import com.pa2aresgi.pa2a.modele.Subscriptions;
import com.pa2aresgi.pa2a.service.SubscriptionsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Profile({"dev", "prod"})
@RestController
@RequestMapping("/subscriptions")
@AllArgsConstructor
public class SubscriptionsController {
    private final SubscriptionsService subscriptionsService;

    @PostMapping("/create")
    public Subscriptions create(@RequestBody Subscriptions subscription){
        return subscriptionsService.create(subscription);
    }

    @GetMapping(value = "/read",  produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Subscriptions> readAll(){
        return subscriptionsService.readAllOrderById();
        //return subscriptionsService.readAll();
    }

    @GetMapping("/read/{id}")
    public Subscriptions findById(@PathVariable Integer id){
        return subscriptionsService.findById(id);
    }


    @PutMapping("/update/{id}")
    public Subscriptions update(@PathVariable Integer id, @RequestBody Subscriptions subscription) {
        return subscriptionsService.update(id, subscription);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return subscriptionsService.deleteById(id);
    }
}

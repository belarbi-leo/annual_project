package com.pa2aresgi.pa2a.controller;

<<<<<<< HEAD
import com.pa2aresgi.pa2a.enumeratation.AuthorizationSvcEnum;
=======
import com.pa2aresgi.pa2a.enumeratation.Svc_authorization;
>>>>>>> d76060e (feat: signin)
import com.pa2aresgi.pa2a.modele.Services;
import com.pa2aresgi.pa2a.service.ServicesService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Profile("dev")
@RestController
@RequestMapping("/services")
@AllArgsConstructor
public class ServicesController {

    private final ServicesService servicesService;

    @PostMapping("/create")
    public Services create(@RequestBody Services service){
        return servicesService.create(service);
    }

    @GetMapping("/read")
<<<<<<< HEAD
    public List<Services> readAll(@RequestParam(name = "auth", required = false) AuthorizationSvcEnum auth){
=======
    public List<Services> readAll(@RequestParam(name = "auth", required = false) Svc_authorization auth){
>>>>>>> d76060e (feat: signin)
        if (auth!=null) return servicesService.readAllByAuthOrderById(auth);
        return servicesService.readAllOrderById();
        //return servicesService.readAll();
    }

    @GetMapping("/read/{id}")
    public Services findById(@PathVariable Integer id){
        return servicesService.findById(id);
    }

    @PutMapping("/update/{id}")
    public Services update(@PathVariable Integer id, @RequestBody Services service) {
        return servicesService.update(id, service);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return servicesService.deleteById(id);
    }
}
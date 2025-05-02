package ecodeli.controller;

import ecodeli.enumeratation.AuthorizationSvcEnum;
import ecodeli.modele.Services;
import ecodeli.service.ServicesService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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
    public List<Services> readAll(@RequestParam(name = "auth", required = false) Set<AuthorizationSvcEnum> auth){
        if (auth!=null && !auth.isEmpty()) return servicesService.readAllByAuthIn(auth);
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

package com.pa2aresgi.pa2a.controller;

import com.pa2aresgi.pa2a.modele.RequestsServices;
import com.pa2aresgi.pa2a.service.RequestsServicesService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Profile("dev")
@RestController
@RequestMapping("/requestsSvc")
@AllArgsConstructor
public class RequestsServicesController {

    private final RequestsServicesService requestsServicesService;

    @PostMapping("/create")
    public RequestsServices create(@RequestBody RequestsServices requestSvc){
        return requestsServicesService.create(requestSvc);
    }

    @GetMapping("/read")
    public List<RequestsServices> readAll(){
        return requestsServicesService.readAllOrderById();
        //return requests_servicesService.readAll();
    }

    @GetMapping("/read/{id}")
    public RequestsServices findById(@PathVariable Integer id){
        return requestsServicesService.findById(id);
    }


    @PutMapping("/update/{id}")
    public RequestsServices update(@PathVariable Integer id, @RequestBody RequestsServices requestSvc) {
        return requestsServicesService.update(id, requestSvc);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return requestsServicesService.deleteById(id);
    }
}

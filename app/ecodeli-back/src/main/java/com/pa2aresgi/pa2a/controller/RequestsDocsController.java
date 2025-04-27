package com.pa2aresgi.pa2a.controller;

import com.pa2aresgi.pa2a.modele.RequestsDocs;
import com.pa2aresgi.pa2a.service.RequestsDocsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Profile({"dev", "prod"})
@RestController
@RequestMapping("/requestsDocs")
@AllArgsConstructor
public class RequestsDocsController {

    private final RequestsDocsService requestsDocsService;

    @PostMapping("/create")
    public RequestsDocs create(@RequestBody RequestsDocs requestDoc){
        return requestsDocsService.create(requestDoc);
    }

    @GetMapping("/read")
    public List<RequestsDocs> readAll(){
        return requestsDocsService.readAllOrderById();
        //return requests_docsService.readAll();
    }

    @GetMapping("/read/{id}")
    public RequestsDocs findById(@PathVariable Integer id){
        return requestsDocsService.findById(id);
    }


    @PutMapping("/update/{id}")
    public RequestsDocs update(@PathVariable Integer id, @RequestBody RequestsDocs requestDoc) {
        return requestsDocsService.update(id, requestDoc);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return requestsDocsService.deleteById(id);
    }
}

package com.pa2aresgi.pa2a.controller;

import com.pa2aresgi.pa2a.modele.Payments;
import com.pa2aresgi.pa2a.service.PaymentsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
@AllArgsConstructor
public class PaymentsController {

    private final PaymentsService paymentsService;

    @PostMapping("/create")
    public Payments create(@RequestBody Payments payment){
        return paymentsService.create(payment);
    }

    @GetMapping("/read")
    public List<Payments> readAll(){
        return paymentsService.readAll();
    }

    @GetMapping("/read/{id}")
    public Payments findById(@PathVariable Integer id){
        return paymentsService.findById(id);
    }


    @PutMapping("/update/{id}")
    public Payments update(@PathVariable Integer id, @RequestBody Payments payment) {
        return paymentsService.update(id, payment);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return paymentsService.deleteById(id);
    }
}

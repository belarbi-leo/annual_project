package com.pa2aresgi.pa2a.controller;

import com.pa2aresgi.pa2a.modele.Produit;
import com.pa2aresgi.pa2a.service.ProduitService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produit")
@AllArgsConstructor
public class ProduitController {
    private final ProduitService produitService;

    @PostMapping("/create")
    public Produit create(@RequestBody Produit produit){
        return produitService.creer(produit);
    }

    @GetMapping("/read")
    public List<Produit> read(){
        return produitService.lire();
    }

    @PutMapping("/update/{id}")
    public Produit update(@PathVariable long id, @RequestBody Produit produit) {
        return produitService.modifier(id, produit);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        return produitService.supprimer(id);
    }
}

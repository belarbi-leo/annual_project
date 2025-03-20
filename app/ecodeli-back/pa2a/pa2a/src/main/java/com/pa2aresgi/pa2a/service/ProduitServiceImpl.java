package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.modele.Produit;
import com.pa2aresgi.pa2a.repository.ProduitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProduitServiceImpl implements ProduitService {

    private ProduitRepository produitRepository;

    @Override
    public Produit creer(Produit produit) {
        return produitRepository.save(produit);
    }

    @Override
    public List<Produit> lire() {
        return produitRepository.findAll();
    }

    @Override
    public Produit modifier(long id, Produit produit) {
        return produitRepository.findById(id).map(p -> {
            p.setNom(produit.getNom());
            p.setDescription(produit.getDescription());
            p.setPrix(produit.getPrix());
            return produitRepository.save(p);
        }).orElseThrow(() -> new RuntimeException("Produit non trouvé !"));
    }

    @Override
    public String supprimer(long id) {
        produitRepository.deleteById(id);
        return "Produit supprimé!";
    }
}

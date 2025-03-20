package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.modele.Produit;

import java.util.List;

public interface ProduitService {

    Produit creer(Produit produit);

    List<Produit> lire();

    Produit modifier(long id, Produit produit);

    String supprimer(long id);
}

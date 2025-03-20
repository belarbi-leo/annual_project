package com.pa2aresgi.pa2a.repository;

import com.pa2aresgi.pa2a.modele.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
}

package com.pa2aresgi.pa2a.repository;

import com.pa2aresgi.pa2a.modele.Packages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PackagesRepository extends JpaRepository<Packages,Integer> {
    @Query("select pack from Packages pack order by pack.idPack")
    List<Packages> findAllOrderById_pack();
}

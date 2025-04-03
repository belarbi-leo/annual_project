package com.pa2aresgi.pa2a.repository;

import com.pa2aresgi.pa2a.modele.Routes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoutesRepository extends JpaRepository<Routes, Integer> {
    @Query("select route from Routes route order by route.id_route")
    List<Routes> findAllOrderById_route();
}

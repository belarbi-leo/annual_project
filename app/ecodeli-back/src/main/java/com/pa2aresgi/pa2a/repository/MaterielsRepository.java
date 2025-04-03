package com.pa2aresgi.pa2a.repository;

import com.pa2aresgi.pa2a.modele.Materiels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MaterielsRepository extends JpaRepository<Materiels,Integer> {
    @Query("select mat from Materiels mat order by mat.id_mat")
    List<Materiels> findAllOrderById_mat();
}

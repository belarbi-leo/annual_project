package com.pa2aresgi.pa2a.repository;

import com.pa2aresgi.pa2a.modele.Disputes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DisputesRepository extends JpaRepository<Disputes, Integer> {
    @Query("select dis from Disputes dis order by dis.id_dispute")
    List<Disputes> findAllOrderById_dispute();
}

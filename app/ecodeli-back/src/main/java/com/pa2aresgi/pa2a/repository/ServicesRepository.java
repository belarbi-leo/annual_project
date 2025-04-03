package com.pa2aresgi.pa2a.repository;

import com.pa2aresgi.pa2a.modele.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServicesRepository extends JpaRepository<Services,Integer> {
    @Query("select svc from Services svc order by svc.id_svc")
    List<Services> findAllOrderById_svc();
}

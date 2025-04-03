package com.pa2aresgi.pa2a.repository;

import com.pa2aresgi.pa2a.modele.Requests_svc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Requests_svcRepository extends JpaRepository<Requests_svc,Integer> {
    @Query("select req_svc from Requests_svc req_svc order by req_svc.id_req_svc")
    List<Requests_svc> findAllOrderById_req_svc();
}

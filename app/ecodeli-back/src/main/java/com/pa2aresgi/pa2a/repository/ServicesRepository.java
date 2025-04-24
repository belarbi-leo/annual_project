package com.pa2aresgi.pa2a.repository;

import com.pa2aresgi.pa2a.enumeratation.AuthorizationSvcEnum;
import com.pa2aresgi.pa2a.modele.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServicesRepository extends JpaRepository<Services,Integer> {
    @Query("select svc from Services svc order by svc.idSvc")
    List<Services> findAllOrderById_svc();

    List<Services> findAllByAuth(AuthorizationSvcEnum auth);

    @Query("select svc from Services svc where svc.auth = ?1 order by svc.idSvc")
    List<Services> findAllByRoleOrderById_svc(AuthorizationSvcEnum auth);
}

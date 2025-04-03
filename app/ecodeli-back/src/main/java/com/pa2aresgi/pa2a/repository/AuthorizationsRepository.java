package com.pa2aresgi.pa2a.repository;

import com.pa2aresgi.pa2a.modele.Authorizations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorizationsRepository extends JpaRepository<Authorizations, Integer> {
    @Query("select auth from Authorizations auth order by auth.id_auth")
    List<Authorizations> findAllOrderById_auth();
}

package com.pa2aresgi.pa2a.repository;

import com.pa2aresgi.pa2a.enumeratation.Role_enum;
import com.pa2aresgi.pa2a.enumeratation.Svc_authorization;
import com.pa2aresgi.pa2a.modele.Services;
import com.pa2aresgi.pa2a.modele.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServicesRepository extends JpaRepository<Services,Integer> {
    @Query("select svc from Services svc order by svc.id_svc")
    List<Services> findAllOrderById_svc();

    List<Services> findAllByAuth(Svc_authorization auth);

    @Query("select user from Users user order by user.id_user")
    List<Users> findAllOrderById_user();

    @Query("select user from Users user where user.role = ?1 order by user.id_user")
    List<Users> findAllByRoleOrderById_user(Role_enum role);
}

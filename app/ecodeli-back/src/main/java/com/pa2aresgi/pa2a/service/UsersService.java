package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.enumeratation.RoleEnum;
import com.pa2aresgi.pa2a.modele.Users;

import java.util.List;

public interface UsersService {
    Users create(Users user);

    List<Users> readAll();

    List<Users> readAllOrderById();

    List<Users> readAllByRole(RoleEnum role);

    List<Users> readAllByRoleOrderById(RoleEnum role);

    Users findById(Integer id);

    Users update(Integer id, Users user);

    String deleteById(Integer id);
}

package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.DTO.create.UsersDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.UsersDTORead;
import com.pa2aresgi.pa2a.enumeratation.Role_enum;
import com.pa2aresgi.pa2a.modele.Users;

import java.util.List;

public interface UsersServiceDTO {
    UsersDTORead create(UsersDTOCreate userDtoCreate);

    List<UsersDTORead> readAll();

    List<UsersDTORead> readAllOrderById();

    List<UsersDTORead> readAllByRole(Role_enum role);

    List<UsersDTORead> readAllByRoleOrderById(Role_enum role);

    UsersDTORead findById(Integer id);

    UsersDTORead update(Integer id, UsersDTOCreate userDTOCreate);

    String deleteById(Integer id);
}

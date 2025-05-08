package ecodeli.service;

import ecodeli.enumeratation.RoleEnum;
import ecodeli.modele.Users;

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

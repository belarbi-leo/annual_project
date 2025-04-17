package com.pa2aresgi.pa2a.controller;

import com.pa2aresgi.pa2a.DTO.create.UsersDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.UsersDTORead;
import com.pa2aresgi.pa2a.enumeratation.Role_enum;
import com.pa2aresgi.pa2a.modele.Users;
import com.pa2aresgi.pa2a.service.UsersService;
import com.pa2aresgi.pa2a.service.UsersServiceDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usersDTO")
@AllArgsConstructor
public class UsersDTOController {
    private final UsersServiceDTO usersServiceDTO;

    @PostMapping("/create")
    public UsersDTORead create(@RequestBody UsersDTOCreate userDTOCreate){
        return usersServiceDTO.create(userDTOCreate);
    }

    @GetMapping("/read")
    public List<UsersDTORead> readAll(@RequestParam(name = "role", required = false) Role_enum role){
        if (role!=null) return usersServiceDTO.readAllByRoleOrderById(role);
        return usersServiceDTO.readAllOrderById();
        /*if (role!=null) return usersService.readAllByRole(role);
        return usersService.readAll();*/
    }

    @GetMapping("/read/{id}")
    public UsersDTORead findById(@PathVariable Integer id){
        return usersServiceDTO.findById(id);
    }


    @PutMapping("/update/{id}")
    public UsersDTORead update(@PathVariable Integer id, @RequestBody UsersDTOCreate user) {
        return usersServiceDTO.update(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return usersServiceDTO.deleteById(id);
    }
}

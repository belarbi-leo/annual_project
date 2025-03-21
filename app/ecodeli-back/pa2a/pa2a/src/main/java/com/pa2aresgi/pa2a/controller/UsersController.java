package com.pa2aresgi.pa2a.controller;

import com.pa2aresgi.pa2a.modele.Users;
import com.pa2aresgi.pa2a.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @PostMapping("/create")
    public Users create(@RequestBody Users user){
        return usersService.create(user);
    }

    @GetMapping("/read")
    public List<Users> readAll(){
        return usersService.readAll();
    }

    @GetMapping("/read/{id}")
    public Users findById(@PathVariable Integer id){
        return usersService.findById(id);
    }


    @PutMapping("/update/{id}")
    public Users update(@PathVariable Integer id, @RequestBody Users user) {
        return usersService.update(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return usersService.deleteById(id);
    }
}

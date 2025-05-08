package ecodeli.controller;

import ecodeli.enumeratation.RoleEnum;
import ecodeli.modele.Users;
import ecodeli.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Profile("dev")
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
    public List<Users> readAll(@RequestParam(name = "role", required = false) RoleEnum role){
        if (role!=null) return usersService.readAllByRoleOrderById(role);
        return usersService.readAllOrderById();
        /*if (role!=null) return usersService.readAllByRole(role);
        return usersService.readAll();*/
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

package ecodeli.controller;
/*
import ecodeli.modele.intermediairesInutiles.Authorizations;
import ecodeli.service.AuthorizationsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authorizations")
@AllArgsConstructor
public class AuthorizationsController {

    private final AuthorizationsService authorizationsService;

    @PostMapping("/create")
    public Authorizations create(@RequestBody Authorizations authorization){
        return authorizationsService.create(authorization);
    }

    @GetMapping("/read")
    public List<Authorizations> readAll(){
        return authorizationsService.readAllOrderById();
        //return authorizationsService.readAll();
    }

    @GetMapping("/read/{id}")
    public Authorizations findById(@PathVariable Integer id){
        return authorizationsService.findById(id);
    }


    @PutMapping("/update/{id}")
    public Authorizations update(@PathVariable Integer id, @RequestBody Authorizations authorization) {
        return authorizationsService.update(id, authorization);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return authorizationsService.deleteById(id);
    }
}
*/
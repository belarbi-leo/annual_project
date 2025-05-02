package ecodeli.controller;

import ecodeli.modele.Disputes;
import ecodeli.service.DisputesService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Profile("dev")
@RestController
@RequestMapping("/disputes")
@AllArgsConstructor
public class DisputesController {

    private final DisputesService disputesService;

    @PostMapping("/create")
    public Disputes create(@RequestBody Disputes dispute){
        return disputesService.create(dispute);
    }

    @GetMapping("/read")
    public List<Disputes> readAll(){
        return disputesService.readAllOrderById();
        //return disputesService.readAll();
    }

    @GetMapping("/read/{id}")
    public Disputes findById(@PathVariable Integer id){
        return disputesService.findById(id);
    }


    @PutMapping("/update/{id}")
    public Disputes update(@PathVariable Integer id, @RequestBody Disputes dispute) {
        return disputesService.update(id, dispute);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return disputesService.deleteById(id);
    }
}
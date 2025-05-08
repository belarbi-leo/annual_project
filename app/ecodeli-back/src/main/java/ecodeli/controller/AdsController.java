package ecodeli.controller;

import ecodeli.modele.Ads;
import ecodeli.service.AdsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Profile("dev")
@RestController
@RequestMapping("/ads")
@AllArgsConstructor
public class AdsController {

    private final AdsService adsService;

    @PostMapping("/create")
    public Ads create(@RequestBody Ads ad){
        return adsService.create(ad);
    }

    @GetMapping("/read")
    public List<Ads> readAll(){
        return adsService.readAllOrderById();
        //return adsService.readAll();
    }

    @GetMapping("/read/{id}")
    public Ads findById(@PathVariable Integer id){
        return adsService.findById(id);
    }


    @PutMapping("/update/{id}")
    public Ads update(@PathVariable Integer id, @RequestBody Ads ad) {
        return adsService.update(id, ad);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return adsService.deleteById(id);
    }
}
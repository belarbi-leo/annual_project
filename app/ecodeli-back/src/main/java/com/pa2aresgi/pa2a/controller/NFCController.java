package com.pa2aresgi.pa2a.controller;

import com.pa2aresgi.pa2a.modele.NFC;
import com.pa2aresgi.pa2a.service.NFCService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Profile("dev")
@RestController
@RequestMapping("/nfc")
@AllArgsConstructor
public class NFCController {
    private final NFCService nfcService;

    @PostMapping("/create")
    public NFC create(@RequestBody NFC nfc){
        return nfcService.create(nfc);
    }

    @GetMapping("/read")
    public List<NFC> readAll(){
        return nfcService.readAllOrderById();
        //return nfcService.readAll();
    }

    @GetMapping("/read/{id}")
    public NFC findById(@PathVariable Integer id){
        return nfcService.findById(id);
    }


    @PutMapping("/update/{id}")
    public NFC update(@PathVariable Integer id, @RequestBody NFC nfc) {
        return nfcService.update(id, nfc);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return nfcService.deleteById(id);
    }
}

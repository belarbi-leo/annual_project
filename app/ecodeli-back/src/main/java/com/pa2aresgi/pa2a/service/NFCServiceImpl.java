package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.modele.Languages;
import com.pa2aresgi.pa2a.modele.NFC;
import com.pa2aresgi.pa2a.repository.NFCRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NFCServiceImpl implements NFCService {
    private NFCRepository nfcRepository;

    @Override
    public NFC create(NFC nfc) {
        return nfcRepository.save(nfc);
    }

    @Override
    public List<NFC> readAll() {
        return nfcRepository.findAll();
    }

    @Override
    public List<NFC> readAllOrderById() {
        return nfcRepository.findAllOrderById_card_nfc();
    }

    @Override
    public NFC findById(Integer id) {
        if (nfcRepository.findById(id).isPresent()){
            return nfcRepository.findById(id).get();
        } else {
            throw new RuntimeException("NFC not found ! ");
        }
    }

    @Override
    public NFC update(Integer id, NFC nfc) {
        return nfcRepository.findById(id).map(nfc1 -> {
            nfc1.setId_user(nfc.getId_user());
            return nfcRepository.save(nfc1);
        }).orElseThrow(() -> new RuntimeException("NFC not found !"));
    }

    @Override
    public String deleteById(Integer id) {
        nfcRepository.deleteById(id);
        return "NFC deleted !";
    }
}

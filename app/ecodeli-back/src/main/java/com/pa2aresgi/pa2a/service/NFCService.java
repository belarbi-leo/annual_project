package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.modele.NFC;

import java.util.List;

public interface NFCService {
    NFC create(NFC nfc);

    List<NFC> readAll();

    List<NFC> readAllOrderById();

    NFC findById(Integer id);

    NFC update(Integer id, NFC nfc);

    String deleteById(Integer id);
}

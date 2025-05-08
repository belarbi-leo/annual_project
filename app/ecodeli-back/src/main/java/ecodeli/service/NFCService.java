package ecodeli.service;

import ecodeli.modele.NFC;

import java.util.List;

public interface NFCService {
    NFC create(NFC nfc);

    List<NFC> readAll();

    List<NFC> readAllOrderById();

    NFC findById(Integer id);

    NFC update(Integer id, NFC nfc);

    String deleteById(Integer id);
}

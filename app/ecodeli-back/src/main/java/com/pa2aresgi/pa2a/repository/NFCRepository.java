package com.pa2aresgi.pa2a.repository;

import com.pa2aresgi.pa2a.modele.NFC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NFCRepository extends JpaRepository<NFC, Integer> {
    @Query("select nfc from NFC nfc order by nfc.id_card_nfc")
    List<NFC> findAllOrderById_card_nfc();
}

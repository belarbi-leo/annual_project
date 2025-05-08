package ecodeli.repository;

import ecodeli.modele.NFC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NFCRepository extends JpaRepository<NFC, Integer> {
    @Query("select nfc from NFC nfc order by nfc.idCard")
    List<NFC> findAllOrderById_card_nfc();
}

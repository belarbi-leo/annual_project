package ecodeli.repository;

import ecodeli.modele.Disputes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DisputesRepository extends JpaRepository<Disputes, Integer> {
    @Query("select dis from Disputes dis order by dis.idDispute")
    List<Disputes> findAllOrderById_dispute();
}

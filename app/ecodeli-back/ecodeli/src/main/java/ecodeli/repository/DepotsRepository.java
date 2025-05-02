package ecodeli.repository;

import ecodeli.modele.Depots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepotsRepository extends JpaRepository<Depots, Integer> {
    @Query("select dep from Depots dep order by dep.idDepot")
    List<Depots> findAllOrderById_depot();
}

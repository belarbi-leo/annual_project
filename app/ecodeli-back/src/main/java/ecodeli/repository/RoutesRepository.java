package ecodeli.repository;

import ecodeli.modele.Routes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoutesRepository extends JpaRepository<Routes, Integer> {
    @Query("select route from Routes route order by route.idRoute")
    List<Routes> findAllOrderById_route();
}

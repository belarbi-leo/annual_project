package ecodeli.repository;

import ecodeli.modele.Opinions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OpinionsRepository extends JpaRepository<Opinions, Integer> {
    @Query("select op from Opinions op order by op.idOpinion")
    List<Opinions> findAllOrderById_opinion();
}

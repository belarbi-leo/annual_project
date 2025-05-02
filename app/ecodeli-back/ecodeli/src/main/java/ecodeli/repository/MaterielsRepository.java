package ecodeli.repository;

import ecodeli.modele.Materiels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MaterielsRepository extends JpaRepository<Materiels,Integer> {
    @Query("select mat from Materiels mat order by mat.idMateriel")
    List<Materiels> findAllOrderById_mat();
}

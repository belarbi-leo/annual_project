package ecodeli.repository;

import ecodeli.modele.ServicesDocs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServicesDocsRepository extends JpaRepository<ServicesDocs,Integer> {
    @Query("select svcDoc from ServicesDocs svcDoc order by svcDoc.id_doc_svc")
    List<ServicesDocs> findAllOrderById_doc_svc();
}

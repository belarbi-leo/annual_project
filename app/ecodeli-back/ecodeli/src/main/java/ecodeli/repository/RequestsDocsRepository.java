package ecodeli.repository;

import ecodeli.modele.RequestsDocs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RequestsDocsRepository extends JpaRepository<RequestsDocs,Integer> {
    @Query("select reqDoc from RequestsDocs reqDoc order by reqDoc.idDocReq")
    List<RequestsDocs> findAllOrderById_doc_req();
}

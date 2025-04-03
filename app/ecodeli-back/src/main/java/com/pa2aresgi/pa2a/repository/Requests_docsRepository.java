package com.pa2aresgi.pa2a.repository;

import com.pa2aresgi.pa2a.modele.Requests_docs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Requests_docsRepository extends JpaRepository<Requests_docs,Integer> {
    @Query("select req_doc from Requests_docs req_doc order by req_doc.id_doc_req")
    List<Requests_docs> findAllOrderById_doc_req();
}

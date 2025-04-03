package com.pa2aresgi.pa2a.repository;

import com.pa2aresgi.pa2a.modele.Services_docs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Services_docsRepository extends JpaRepository<Services_docs,Integer> {
    @Query("select svc_doc from Services_docs svc_doc order by svc_doc.id_doc_svc")
    List<Services_docs> findAllOrderById_doc_svc();
}

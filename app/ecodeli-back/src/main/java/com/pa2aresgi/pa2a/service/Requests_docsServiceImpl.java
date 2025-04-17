package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.modele.Requests_docs;
import com.pa2aresgi.pa2a.repository.Requests_docsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class Requests_docsServiceImpl implements Requests_docsService {
    private Requests_docsRepository requests_docsRepository;

    @Override
    public Requests_docs create(Requests_docs request_doc) {
        return requests_docsRepository.save(request_doc);
    }

    @Override
    public List<Requests_docs> readAll() {
        return requests_docsRepository.findAll();
    }

    @Override
    public List<Requests_docs> readAllOrderById() {
        return requests_docsRepository.findAllOrderById_doc_req();
    }

    @Override
    public Requests_docs findById(Integer id) {
        if (requests_docsRepository.findById(id).isPresent()){
            return requests_docsRepository.findById(id).get();
        } else {
            throw new RuntimeException("Request_doc not found ! ");
        }
    }

    @Override
    public Requests_docs update(Integer id, Requests_docs request_doc) {
        return requests_docsRepository.findById(id).map(rq_doc -> {
            rq_doc.setId_req_svc(request_doc.getId_req_svc());
            //rq_doc.setDoc_type_req(request_doc.getDoc_type_req());
            rq_doc.setDoc_req(request_doc.getDoc_req());
            rq_doc.setComment(request_doc.getComment());
            return requests_docsRepository.save(rq_doc);
        }).orElseThrow(() -> new RuntimeException("Request_doc not found !"));
    }

    @Override
    public String deleteById(Integer id) {
        requests_docsRepository.deleteById(id);
        return "Request_doc deleted !";
    }
}
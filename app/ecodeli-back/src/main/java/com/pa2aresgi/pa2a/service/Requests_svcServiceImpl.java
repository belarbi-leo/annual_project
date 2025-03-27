package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.modele.Requests_svc;
import com.pa2aresgi.pa2a.repository.Requests_svcRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class Requests_svcServiceImpl implements Requests_svcService {
    private Requests_svcRepository requests_svcRepository;

    @Override
    public Requests_svc create(Requests_svc request_svc) {
        return requests_svcRepository.save(request_svc);
    }

    @Override
    public List<Requests_svc> readAll() {
        return requests_svcRepository.findAll();
    }

    @Override
    public Requests_svc findById(Integer id) {
        if (requests_svcRepository.findById(id).isPresent()){
            return requests_svcRepository.findById(id).get();
        } else {
            throw new RuntimeException("Request_svc not found ! ");
        }
    }

    @Override
    public Requests_svc update(Integer id, Requests_svc request_svc) {
        return requests_svcRepository.findById(id).map(rq_svc -> {
            rq_svc.setId_user_req(request_svc.getId_user_req());
            rq_svc.setId_admin_res(request_svc.getId_admin_res());
            rq_svc.setId_svc(request_svc.getId_svc());
            rq_svc.setStatus_req(request_svc.getStatus_req());
            rq_svc.setDate_req(request_svc.getDate_req());
            rq_svc.setDate_res(request_svc.getDate_res());
            rq_svc.setReason_res(request_svc.getReason_res());/*
            rq_svc.setRequests_docs_list(request_svc.getRequests_docs_list());*/
            return requests_svcRepository.save(rq_svc);
        }).orElseThrow(() -> new RuntimeException("Request_svc not found !"));
    }

    @Override
    public String deleteById(Integer id) {
        requests_svcRepository.deleteById(id);
        return "Request_svc deleted !";
    }
}
package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.modele.Services_docs;
import com.pa2aresgi.pa2a.repository.Services_docsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class Services_docsServiceImpl implements Services_docsService {
    private Services_docsRepository services_docsRepository;

    @Override
    public Services_docs create(Services_docs service_doc) {
        return services_docsRepository.save(service_doc);
    }

    @Override
    public List<Services_docs> readAll() {
        return services_docsRepository.findAll();
    }

    @Override
    public Services_docs findById(Integer id) {
        if (services_docsRepository.findById(id).isPresent()){
            return services_docsRepository.findById(id).get();
        } else {
            throw new RuntimeException("Service_doc not found ! ");
        }
    }

    @Override
    public Services_docs update(Integer id, Services_docs service_doc) {
        return services_docsRepository.findById(id).map(svc_doc -> {
            svc_doc.setId_svc(service_doc.getId_svc());
            svc_doc.setName_doc(service_doc.getName_doc());
            return services_docsRepository.save(svc_doc);
        }).orElseThrow(() -> new RuntimeException("Service_doc not found !"));
    }

    @Override
    public String deleteById(Integer id) {
        services_docsRepository.deleteById(id);
        return "Service_doc deleted !";
    }
}

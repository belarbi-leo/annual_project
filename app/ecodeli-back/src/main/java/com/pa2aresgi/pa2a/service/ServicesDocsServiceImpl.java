package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.modele.ServicesDocs;
import com.pa2aresgi.pa2a.repository.ServicesDocsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServicesDocsServiceImpl implements ServicesDocsService {
    private ServicesDocsRepository servicesDocsRepository;

    @Override
    public ServicesDocs create(ServicesDocs serviceDoc) {
        return servicesDocsRepository.save(serviceDoc);
    }

    @Override
    public List<ServicesDocs> readAll() {
        return servicesDocsRepository.findAll();
    }

    @Override
    public List<ServicesDocs> readAllOrderById() {
        return servicesDocsRepository.findAllOrderById_doc_svc();
    }

    @Override
    public ServicesDocs findById(Integer id) {
        if (servicesDocsRepository.findById(id).isPresent()){
            return servicesDocsRepository.findById(id).get();
        } else {
            throw new RuntimeException("ServiceDoc not found ! ");
        }
    }

    @Override
    public ServicesDocs update(Integer id, ServicesDocs serviceDoc) {
        return servicesDocsRepository.findById(id).map(svcDoc -> {
            //svc_doc.setId_svc(service_doc.getId_svc());
            svcDoc.setNameDoc(serviceDoc.getNameDoc());
            return servicesDocsRepository.save(svcDoc);
        }).orElseThrow(() -> new RuntimeException("ServiceDoc not found !"));
    }

    @Override
    public String deleteById(Integer id) {
        servicesDocsRepository.deleteById(id);
        return "ServiceDoc deleted !";
    }
}

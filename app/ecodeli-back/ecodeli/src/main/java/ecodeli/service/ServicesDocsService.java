package ecodeli.service;

import ecodeli.modele.ServicesDocs;

import java.util.List;

public interface ServicesDocsService {
    ServicesDocs create(ServicesDocs serviceDoc);

    List<ServicesDocs> readAll();

    List<ServicesDocs> readAllOrderById();

    ServicesDocs findById(Integer id);

    ServicesDocs update(Integer id, ServicesDocs serviceDoc);

    String deleteById(Integer id);
}

package ecodeli.service;

import ecodeli.enumeratation.AuthorizationSvcEnum;
import ecodeli.modele.*;
import ecodeli.repository.ServicesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ServicesServiceImpl implements ServicesService {
    private ServicesRepository servicesRepository;

    @Override
    public Services create(Services service) {
        return servicesRepository.save(service);
    }

    @Override
    public List<Services> readAll() {
        return servicesRepository.findAll();
    }

    @Override
    public List<Services> readAllOrderById() {
        return servicesRepository.findAllOrderById_svc();
    }

    @Override
    public List<Services> readAllByAuth(AuthorizationSvcEnum auth){
        return servicesRepository.findAllByAuth(auth);
    }

    @Override
    public List<Services> readAllByAuthIn(Set<AuthorizationSvcEnum> auths){
        return servicesRepository.findAllByAuthIn(auths);
    }

    @Override
    public List<Services> readAllByAuthOrderById(AuthorizationSvcEnum auth){
        return servicesRepository.findAllByAuthOrderById_svc(auth);
    }

    @Override
    public Services findById(Integer id) {
        if (servicesRepository.findById(id).isPresent()){
            return servicesRepository.findById(id).get();
        } else {
            throw new RuntimeException("Service not found ! ");
        }
    }

    @Override
    public Services update(Integer id, Services service) {
        return servicesRepository.findById(id).map(svc -> {
            //svc.setId_admin_creator(service.getId_admin_creator());
            svc.setDateCreationSvc(service.getDateCreationSvc());
            svc.setNameSvc(service.getNameSvc());
            svc.setCategory(service.getCategory());
            svc.setAuth(service.getAuth());/*
            svc.setServices_docs_list(service.getServices_docs_list());
            svc.setMateriels_list(service.getMateriels_list());
            svc.setAuthorizations_list(service.getAuthorizations_list());
            svc.setRequests_svc_list(service.getRequests_svc_list());
            svc.setAds_list(service.getAds_list());*/
            return servicesRepository.save(svc);
        }).orElseThrow(() -> new RuntimeException("Service not found !"));
    }

    @Override
    public String deleteById(Integer id) {
        servicesRepository.deleteById(id);
        return "Service deleted !";
    }
}

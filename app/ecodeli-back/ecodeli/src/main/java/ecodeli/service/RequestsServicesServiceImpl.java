package ecodeli.service;

import ecodeli.modele.RequestsServices;
import ecodeli.repository.RequestsServicesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RequestsServicesServiceImpl implements RequestsServicesService {
    private RequestsServicesRepository requestsServicesRepository;

    @Override
    public RequestsServices create(RequestsServices requestSvc) {
        return requestsServicesRepository.save(requestSvc);
    }

    @Override
    public List<RequestsServices> readAll() {
        return requestsServicesRepository.findAll();
    }

    @Override
    public List<RequestsServices> readAllOrderById() {
        return requestsServicesRepository.findAllOrderById_req_svc();
    }

    @Override
    public RequestsServices findById(Integer id) {
        if (requestsServicesRepository.findById(id).isPresent()){
            return requestsServicesRepository.findById(id).get();
        } else {
            throw new RuntimeException("RequestServices not found ! ");
        }
    }

    @Override
    public RequestsServices update(Integer id, RequestsServices requestSvc) {
        return requestsServicesRepository.findById(id).map(rqSvc -> {
            rqSvc.setUserReq(requestSvc.getUserReq());
            rqSvc.setAdminRes(requestSvc.getAdminRes());
            rqSvc.setSvc(requestSvc.getSvc());
            rqSvc.setStatusReq(requestSvc.getStatusReq());
            rqSvc.setDateReq(requestSvc.getDateReq());
            rqSvc.setDateRes(requestSvc.getDateRes());
            rqSvc.setReasonRes(requestSvc.getReasonRes());/*
            rq_svc.setRequests_docs_list(request_svc.getRequests_docs_list());*/
            return requestsServicesRepository.save(rqSvc);
        }).orElseThrow(() -> new RuntimeException("RequestServices not found !"));
    }

    @Override
    public String deleteById(Integer id) {
        requestsServicesRepository.deleteById(id);
        return "RequestServices deleted !";
    }
}
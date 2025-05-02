package ecodeli.modelMapper.subClassImpl;

import ecodeli.DTO.create.RequestsServicesDTOCreate;
import ecodeli.DTO.read.RequestsServicesDTORead;
import ecodeli.modelMapper.RequestsServicesMapper;
import ecodeli.modele.RequestsServices;
import ecodeli.modele.Services;
import ecodeli.modele.Users;
import ecodeli.repository.ServicesRepository;
import ecodeli.repository.UsersRepository;
import ecodeli.tools.ResponseErrorV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequestsServicesMapperSubClassImpl {
    private final RequestsServicesMapper requestsServicesMapper;
    private final UsersRepository usersRepository;
    private final ServicesRepository servicesRepository;

    public RequestsServicesDTORead toDtoRead(RequestsServices requestService){
        return requestsServicesMapper.toDtoRead(requestService);
    }

    public Object fromDtoCreate(RequestsServicesDTOCreate requestServiceDtoCreate){
        RequestsServices requestService = requestsServicesMapper.fromDtoCreate(requestServiceDtoCreate);
        Optional<Users> userReq = usersRepository.findById(requestServiceDtoCreate.getUserReq());
        Optional<Users> adminRes = usersRepository.findById(requestServiceDtoCreate.getAdminRes());
        Optional<Services> service = servicesRepository.findById(requestServiceDtoCreate.getSvc());
        ResponseErrorV2 retourNeg = new ResponseErrorV2();
        if (userReq.isEmpty()) retourNeg.add("userReq", "Id for the user making the request not found !");
        else requestService.setUserReq(userReq.get());
        if (adminRes.isEmpty()) retourNeg.add("adminRes", "Id for the admin responding the request not found !");
        else requestService.setAdminRes(adminRes.get());
        if (service.isEmpty()) retourNeg.add("service", "Id for the service for the request not found !");
        else requestService.setSvc(service.get());
        if (!retourNeg.getErrors().isEmpty())
            return retourNeg;
        else
            return requestService;
    }

    public Object updateRequestServiceFromDtoCreate(RequestsServicesDTOCreate requestServiceDtoCreate, RequestsServices requestService){
        RequestsServices rqSvc = requestsServicesMapper.updateRequestServiceFromDtoCreate(requestServiceDtoCreate, requestService);
        ResponseErrorV2 retourNeg = new ResponseErrorV2();
        if (requestServiceDtoCreate.getUserReq() != null){
            Optional<Users> userReq = usersRepository.findById(requestServiceDtoCreate.getUserReq());
            if (userReq.isEmpty()) retourNeg.add("userReq", "Id for the user making the request not found !");
            else rqSvc.setUserReq(userReq.get());
        }
        if (requestServiceDtoCreate.getAdminRes() != null){
            Optional<Users> adminRes = usersRepository.findById(requestServiceDtoCreate.getAdminRes());
            if (adminRes.isEmpty()) retourNeg.add("adminRes", "Id for the admin responding the request not found !");
            else rqSvc.setAdminRes(adminRes.get());
        }
        if (requestServiceDtoCreate.getSvc() != null){
            Optional<Services> service = servicesRepository.findById(requestServiceDtoCreate.getSvc());
            if (service.isEmpty()) retourNeg.add("service", "Id for the service for the request not found !");
            else rqSvc.setSvc(service.get());
        }
        if (!retourNeg.getErrors().isEmpty())
            return retourNeg;
        else
            return rqSvc;
    }
}

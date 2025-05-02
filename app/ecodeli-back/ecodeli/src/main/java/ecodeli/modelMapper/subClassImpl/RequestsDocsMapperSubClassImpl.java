package ecodeli.modelMapper.subClassImpl;

import ecodeli.DTO.create.RequestsDocsDTOCreate;
import ecodeli.DTO.read.RequestsDocsDTORead;
import ecodeli.modelMapper.RequestsDocsMapper;
import ecodeli.modele.RequestsDocs;
import ecodeli.modele.RequestsServices;
import ecodeli.repository.RequestsServicesRepository;
import ecodeli.tools.ResponseErrorV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequestsDocsMapperSubClassImpl {
    private final RequestsDocsMapper requestsDocsMapper;
    private final RequestsServicesRepository requestsServicesRepository;

    public RequestsDocsDTORead toDtoRead(RequestsDocs requestDoc) {
        return requestsDocsMapper.toDtoRead(requestDoc);
    }

    public Object fromDtoCreate(RequestsDocsDTOCreate requestDocDtoCreate){
        RequestsDocs requestDoc = requestsDocsMapper.fromDtoCreate(requestDocDtoCreate);
        Optional<RequestsServices> requestService = requestsServicesRepository.findById(requestDocDtoCreate.getReqSvc());
        ResponseErrorV2 retourNeg = new ResponseErrorV2();
        if(requestService.isEmpty()) retourNeg.add("requestSvc", "Id for the linked requested service not found !");
        else requestDoc.setReqSvc(requestService.get());
        if(!retourNeg.getErrors().isEmpty())
            return retourNeg;
        else
            return requestDoc;
    }

    public Object updateRequestDocFromDtoCreate(RequestsDocsDTOCreate requestDocDtoCreate, RequestsDocs requestDoc){
        RequestsDocs rqDoc = requestsDocsMapper.updateRequestDocFromDtoCreate(requestDocDtoCreate, requestDoc);
        ResponseErrorV2 retourNeg = new ResponseErrorV2();
        if (requestDocDtoCreate.getReqSvc() != null){
            Optional<RequestsServices> requestService = requestsServicesRepository.findById(requestDocDtoCreate.getReqSvc());
            if (requestService.isEmpty()) retourNeg.add("requestSvc", "Id for the linked requested service not found !");
            else rqDoc.setReqSvc(requestService.get());
        }
        if(!retourNeg.getErrors().isEmpty())
            return retourNeg;
        else
            return rqDoc;
    }
}

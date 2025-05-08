package ecodeli.modelMapper.subClassImpl;

import ecodeli.DTO.create.ServicesDocsDTOCreate;
import ecodeli.DTO.read.ServicesDocsDTORead;
import ecodeli.modelMapper.ServicesDocsMapper;
import ecodeli.modele.Services;
import ecodeli.modele.ServicesDocs;
import ecodeli.repository.ServicesRepository;
import ecodeli.tools.ResponseErrorV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServicesDocsMapperSubClassImpl {
    private final ServicesDocsMapper servicesDocsMapper;
    private final ServicesRepository servicesRepository;

    public ServicesDocsDTORead toDtoRead(ServicesDocs serviceDoc){
        return servicesDocsMapper.toDtoRead(serviceDoc);
    }

    public Object fromDtoCreate(ServicesDocsDTOCreate serviceDocDtoCreate){
        ServicesDocs serviceDoc = servicesDocsMapper.fromDtoCreate(serviceDocDtoCreate);
        Optional<Services> service = servicesRepository.findById(serviceDocDtoCreate.getSvc());
        ResponseErrorV2 retourNeg = new ResponseErrorV2();
        if (service.isEmpty()) retourNeg.add("service","Id service for the service documentation not found !");
        else serviceDoc.setSvc(service.get());
        if (!retourNeg.getErrors().isEmpty())
            return retourNeg;
        else
            return serviceDoc;
    }

    public Object updateServiceDocFromDtoCreate(ServicesDocsDTOCreate serviceDocDtoCreate, ServicesDocs serviceDoc) {
        ServicesDocs svcDoc = servicesDocsMapper.updateServiceDocfromDtoCreate(serviceDocDtoCreate, serviceDoc);
        ResponseErrorV2 retourNeg = new ResponseErrorV2();
        if (serviceDocDtoCreate.getSvc() != null) {
            Optional<Services> service = servicesRepository.findById(serviceDocDtoCreate.getSvc());
            if (service.isEmpty()) retourNeg.add("service", "Id service for the service documentation not found !");
            else svcDoc.setSvc(service.get());
        }
        if (!retourNeg.getErrors().isEmpty())
            return retourNeg;
        else
            return svcDoc;
    }
}

package ecodeli.modelMapper.subClassImpl;

import ecodeli.DTO.create.ServicesDTOCreate;
import ecodeli.DTO.read.ServicesDTORead;
import ecodeli.modelMapper.ServicesMapper;
import ecodeli.modele.Services;
import ecodeli.modele.Users;
import ecodeli.repository.UsersRepository;
import ecodeli.tools.ResponseErrorV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServicesMapperSubClassImpl {
    private final ServicesMapper servicesMapper;
    private final UsersRepository usersRepository;

    public ServicesDTORead toDtoRead(Services service){
        return servicesMapper.toDtoRead(service);
    }

    public Object fromDtoCreate(ServicesDTOCreate serviceDtoCreate){
        Services service = servicesMapper.fromDtoCreate(serviceDtoCreate);
        Optional<Users> adminCreator = usersRepository.findById(serviceDtoCreate.getAdminCreator());
        ResponseErrorV2 retourNeg = new ResponseErrorV2();
        if (adminCreator.isEmpty()) retourNeg.add("adminCreator","Id for admin creator for the service not found !");
        else service.setAdminCreator(adminCreator.get());
        if (!retourNeg.getErrors().isEmpty())
            return retourNeg;
        else
            return service;
    }

    public Object updateServiceFromDtoCreate(ServicesDTOCreate serviceDtoCreate, Services service){
        Services svc = servicesMapper.updateServiceFromDtoCreate(serviceDtoCreate, service);
        ResponseErrorV2 retourNeg = new ResponseErrorV2();
        if(serviceDtoCreate.getAdminCreator() != null){
            Optional<Users> adminCreator = usersRepository.findById(serviceDtoCreate.getAdminCreator());
            if (adminCreator.isEmpty()) retourNeg.add("adminCreator","Id for admin creator for the service not found !");
            else svc.setAdminCreator(adminCreator.get());
        }
        if (!retourNeg.getErrors().isEmpty())
            return retourNeg;
        else
            return svc;
    }
}

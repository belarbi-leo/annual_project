package ecodeli.modelMapper.subClassImpl;

import ecodeli.DTO.create.MaterielsDTOCreate;
import ecodeli.DTO.read.MaterielsDTORead;
import ecodeli.modelMapper.MaterielsMapper;
import ecodeli.modele.Materiels;
import ecodeli.modele.Services;
import ecodeli.repository.ServicesRepository;
import ecodeli.tools.ResponseErrorV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MaterielsMapperSubClassImpl {
    private final MaterielsMapper materielsMapper;
    private final ServicesRepository servicesRepository;

    public MaterielsDTORead toDtoRead(Materiels materiel){
        return materielsMapper.toDtoRead(materiel);
    }

    public Object fromDtoCreate(MaterielsDTOCreate materielDtoCreate){
        Materiels materiel = materielsMapper.fromDtoCreate(materielDtoCreate);
        Optional<Services> service = servicesRepository.findById(materielDtoCreate.getSvc());
        ResponseErrorV2 retourNeg = new ResponseErrorV2();
        if (service.isEmpty()) retourNeg.add("svc", "Id service not found !");
        else materiel.setSvc(service.get());
        if (!retourNeg.getErrors().isEmpty())
            return retourNeg;
        else
            return materiel;
    }

    public Object updateMaterielFromDtoCreate(MaterielsDTOCreate materielDtoCreate, Materiels materiel){
        Materiels mat = materielsMapper.updateMaterielFromDtoCreate(materielDtoCreate, materiel);
        ResponseErrorV2 retourNeg = new ResponseErrorV2();
        if (materielDtoCreate.getSvc() != null){
            Optional<Services> service = servicesRepository.findById(materielDtoCreate.getSvc());
            if (service.isEmpty()) retourNeg.add("svc", "Id service not found !");
            else mat.setSvc(service.get());
        }
        if (!retourNeg.getErrors().isEmpty())
            return retourNeg;
        else
            return mat;
    }
}

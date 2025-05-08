package ecodeli.DTOService;

import ecodeli.DTO.create.ServicesDocsDTOCreate;
import ecodeli.DTO.read.ServicesDocsDTORead;
import ecodeli.modelMapper.subClassImpl.ServicesDocsMapperSubClassImpl;
import ecodeli.modele.ServicesDocs;
import ecodeli.repository.ServicesDocsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServicesDocsDTOServiceImpl implements ServicesDocsDTOService {
    private ServicesDocsRepository servicesDocsDTORepository;
    @Autowired
    private ServicesDocsMapperSubClassImpl servicesDocsMapper;

    @Override
    public Object create(ServicesDocsDTOCreate serviceDocDtoCreate){
        Object obj = servicesDocsMapper.fromDtoCreate(serviceDocDtoCreate);
        if (obj instanceof ServicesDocs)
            return servicesDocsMapper.toDtoRead(servicesDocsDTORepository.save((ServicesDocs) obj));
        else
            return obj;
    }

    @Override
    public List<ServicesDocsDTORead> readAll(){
        return servicesDocsDTORepository.findAll().stream().map(servicesDocsMapper::toDtoRead).toList();
    }

    @Override
    public List<ServicesDocsDTORead> readAll(Sort sort){
        return servicesDocsDTORepository.findAll(sort).stream().map(servicesDocsMapper::toDtoRead).toList();
    }

    @Override
    public Slice<ServicesDocsDTORead> readAll(Pageable pageParam){
        return servicesDocsDTORepository.findAll(pageParam).map(servicesDocsMapper::toDtoRead);
    }

    @Override
    public Optional<ServicesDocsDTORead> findById(Integer id){
        return servicesDocsDTORepository.findById(id).map(servicesDocsMapper::toDtoRead);
    }

    @Override
    public Optional<Object> update(Integer id, ServicesDocsDTOCreate serviceDocDTOCreate){
        return servicesDocsDTORepository.findById(id).map(svcDoc -> {
            Object obj = servicesDocsMapper.updateServiceDocFromDtoCreate(serviceDocDTOCreate,svcDoc);
            if (obj instanceof ServicesDocs)
                return servicesDocsMapper.toDtoRead(servicesDocsDTORepository.save((ServicesDocs) obj));
            else
                return obj;
        });
    }

    @Override
    public Boolean deleteById(Integer id){
        if (servicesDocsDTORepository.existsById(id)){
            servicesDocsDTORepository.deleteById(id);
            return true;
        } else
            return false;
    }
}

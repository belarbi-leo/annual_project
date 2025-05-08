package ecodeli.DTOService;

import ecodeli.DTO.create.ServicesDTOCreate;
import ecodeli.DTO.read.ServicesDTORead;
import ecodeli.enumeratation.AuthorizationSvcEnum;
import ecodeli.modelMapper.subClassImpl.ServicesMapperSubClassImpl;
import ecodeli.modele.Services;
import ecodeli.repository.ServicesRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class SevicesDTOServiceImpl implements ServicesDTOService {
    private ServicesRepository servicesRepository;
    @Autowired
    private ServicesMapperSubClassImpl servicesMapper;

    @Override
    public Object create(ServicesDTOCreate serviceDtoCreate){
        if (serviceDtoCreate.getDateCreationSvc() == null) serviceDtoCreate.setDateCreationSvc(Timestamp.valueOf(LocalDateTime.now()));
        if (serviceDtoCreate.getAuth() == null) serviceDtoCreate.setAuth(AuthorizationSvcEnum.pro);

        Object obj = servicesMapper.fromDtoCreate(serviceDtoCreate);
        if (obj instanceof Services){
            return servicesMapper.toDtoRead(servicesRepository.save((Services)obj));
        } else {
            return obj;
        }
    }

    @Override
    public List<ServicesDTORead> readAll(){
        return servicesRepository.findAll().stream().map(servicesMapper::toDtoRead).toList();
    }

    @Override
    public List<ServicesDTORead> readAll(Sort sort){
        return servicesRepository.findAll(sort).stream().map(servicesMapper::toDtoRead).toList();
    }

    @Override
    public Slice<ServicesDTORead> readAll(Pageable pageParam){
        return servicesRepository.findAll(pageParam).map(servicesMapper::toDtoRead);
    }

    @Override
    public List<ServicesDTORead> readAllByAuthIn(Set<AuthorizationSvcEnum> auth){
        return servicesRepository.findAllByAuthIn(auth).stream().map(servicesMapper::toDtoRead).toList();
    }

    @Override
    public List<ServicesDTORead> readAllByAuthIn(Set<AuthorizationSvcEnum> auth, Sort sort){
        return servicesRepository.findAllByAuthIn(auth, sort).stream().map(servicesMapper::toDtoRead).toList();
    }

    @Override
    public Slice<ServicesDTORead> readAllByAuthIn(Set<AuthorizationSvcEnum> auth, Pageable pageParam){
        return servicesRepository.findAllByAuthIn(auth, pageParam).map(servicesMapper::toDtoRead);
    }

    @Override
    public Optional<ServicesDTORead> findById(Integer id){
        return servicesRepository.findById(id).map(servicesMapper::toDtoRead);
    }

    @Override
    public Optional<Object> update(Integer id, ServicesDTOCreate serviceDTOCreate){
        return servicesRepository.findById(id).map(svc -> {
            Object obj = servicesMapper.updateServiceFromDtoCreate(serviceDTOCreate, svc);
            if (obj instanceof Services){
                return servicesMapper.toDtoRead(servicesRepository.save((Services)obj));
            } else {
                return obj;
            }
        });
    }

    @Override
    public Boolean deleteById(Integer id){
        if (servicesRepository.existsById(id)){
            servicesRepository.deleteById(id);
            return true;
        } else
            return false;
    }
}

package ecodeli.DTOService;

import ecodeli.DTO.create.RequestsServicesDTOCreate;
import ecodeli.DTO.read.RequestsServicesDTORead;
import ecodeli.enumeratation.StatusReqSvcEnum;
import ecodeli.modelMapper.subClassImpl.RequestsServicesMapperSubClassImpl;
import ecodeli.modele.RequestsServices;
import ecodeli.repository.RequestsServicesRepository;
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

@Service
@AllArgsConstructor
public class RequestsServicesDTOServiceImpl implements RequestsServicesDTOService {
    private RequestsServicesRepository requestsServicesRepository;
    @Autowired
    private RequestsServicesMapperSubClassImpl requestsServicesMapper;

    @Override
    public Object create(RequestsServicesDTOCreate requestServiceDtoCreate){
        if (requestServiceDtoCreate.getStatusReq() == null) requestServiceDtoCreate.setStatusReq(StatusReqSvcEnum.pending);
        if (requestServiceDtoCreate.getDateReq() == null) requestServiceDtoCreate.setDateReq(Timestamp.valueOf(LocalDateTime.now()));

        Object obj = requestsServicesMapper.fromDtoCreate(requestServiceDtoCreate);
        if (obj instanceof RequestsServices)
            return requestsServicesMapper.toDtoRead(requestsServicesRepository.save((RequestsServices) obj));
        else
            return obj;
    }

    @Override
    public List<RequestsServicesDTORead> readAll(){
        return requestsServicesRepository.findAll().stream().map(requestsServicesMapper::toDtoRead).toList();
    }

    @Override
    public List<RequestsServicesDTORead> readAll(Sort sort){
        return requestsServicesRepository.findAll(sort).stream().map(requestsServicesMapper::toDtoRead).toList();
    }

    @Override
    public Slice<RequestsServicesDTORead> readAll(Pageable pageParam){
        return requestsServicesRepository.findAll(pageParam).map(requestsServicesMapper::toDtoRead);
    }

    @Override
    public Optional<RequestsServicesDTORead> findById(Integer id){
        return requestsServicesRepository.findById(id).map(requestsServicesMapper::toDtoRead);
    }

    @Override
    public Optional<Object> update(Integer id, RequestsServicesDTOCreate requestServiceDTOCreate){
        return requestsServicesRepository.findById(id).map(requestService -> {
            Object obj = requestsServicesMapper.updateRequestServiceFromDtoCreate(requestServiceDTOCreate, requestService);
            if (obj instanceof RequestsServices)
                return requestsServicesMapper.toDtoRead(requestsServicesRepository.save((RequestsServices) obj));
            else
                return obj;
        });
    }

    @Override
    public Boolean deleteById(Integer id){
        if (requestsServicesRepository.existsById(id)){
            requestsServicesRepository.deleteById(id);
            return true;
        } else
            return false;
    }
}

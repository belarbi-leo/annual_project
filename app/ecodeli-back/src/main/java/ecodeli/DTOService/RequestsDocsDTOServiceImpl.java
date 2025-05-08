package ecodeli.DTOService;

import ecodeli.DTO.create.RequestsDocsDTOCreate;
import ecodeli.DTO.read.RequestsDocsDTORead;
import ecodeli.modelMapper.subClassImpl.RequestsDocsMapperSubClassImpl;
import ecodeli.modele.RequestsDocs;
import ecodeli.repository.RequestsDocsRepository;
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
public class RequestsDocsDTOServiceImpl implements RequestsDocsDTOService {
    private RequestsDocsRepository requestsDocsRepository;
    @Autowired
    private RequestsDocsMapperSubClassImpl requestsDocsMapper;

    @Override
    public Object create(RequestsDocsDTOCreate requestDocDtoCreate){
        Object obj = requestsDocsMapper.fromDtoCreate(requestDocDtoCreate);
        if (obj instanceof RequestsDocs)
            return requestsDocsMapper.toDtoRead(requestsDocsRepository.save((RequestsDocs) obj));
        else
            return obj;
    }

    @Override
    public List<RequestsDocsDTORead> readAll(){
        return requestsDocsRepository.findAll().stream().map(requestsDocsMapper::toDtoRead).toList();
    }

    @Override
    public List<RequestsDocsDTORead> readAll(Sort sort){
        return requestsDocsRepository.findAll(sort).stream().map(requestsDocsMapper::toDtoRead).toList();
    }

    @Override
    public Slice<RequestsDocsDTORead> readAll(Pageable pageParam){
        return requestsDocsRepository.findAll(pageParam).map(requestsDocsMapper::toDtoRead);
    }

    @Override
    public Optional<RequestsDocsDTORead> findById(Integer id){
        return requestsDocsRepository.findById(id).map(requestsDocsMapper::toDtoRead);
    }

    @Override
    public Optional<Object> update(Integer id, RequestsDocsDTOCreate requestDocDTOCreate){
        return requestsDocsRepository.findById(id).map(requestDoc -> {
            Object obj = requestsDocsMapper.updateRequestDocFromDtoCreate(requestDocDTOCreate, requestDoc);
            if (obj instanceof RequestsDocs)
                return requestsDocsMapper.toDtoRead(requestsDocsRepository.save((RequestsDocs) obj));
            else
                return obj;
        });
    }

    @Override
    public Boolean deleteById(Integer id){
        if (requestsDocsRepository.existsById(id)){
            requestsDocsRepository.deleteById(id);
            return true;
        } else
            return false;
    }
}

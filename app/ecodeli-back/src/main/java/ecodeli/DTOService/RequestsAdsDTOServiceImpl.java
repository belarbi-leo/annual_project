package ecodeli.DTOService;

import ecodeli.DTO.create.RequestsAdsDTOCreate;
import ecodeli.DTO.read.RequestsAdsDTORead;
import ecodeli.enumeratation.StatusReqAnnonceEnum;
import ecodeli.modelMapper.subClassImpl.RequestsAdsMapperSubClassImpl;
import ecodeli.modele.RequestsAds;
import ecodeli.repository.RequestsAdsRepository;
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
public class RequestsAdsDTOServiceImpl implements RequestsAdsDTOService {
    private RequestsAdsRepository requestsAdsRepository;
    @Autowired
    private RequestsAdsMapperSubClassImpl requestsAdsMapper;

    @Override
    public Object create(RequestsAdsDTOCreate requestAdDtoCreate){
        if (requestAdDtoCreate.getStatusReqAd() == null) requestAdDtoCreate.setStatusReqAd(StatusReqAnnonceEnum.pending);
        if (requestAdDtoCreate.getDateCreationReqAd() == null) requestAdDtoCreate.setDateCreationReqAd(Timestamp.valueOf(LocalDateTime.now()));
        if (requestAdDtoCreate.getPriceReqAd() == null) requestAdDtoCreate.setPriceReqAd((float) 0.0);

        Object obj = requestsAdsMapper.fromDtoCreate(requestAdDtoCreate);
        if (obj instanceof RequestsAds)
            return requestsAdsMapper.toDtoRead(requestsAdsRepository.save((RequestsAds) obj));
        else
            return obj;
    }

    @Override
    public List<RequestsAdsDTORead> readAll(){
        return requestsAdsRepository.findAll().stream().map(requestsAdsMapper::toDtoRead).toList();
    }

    @Override
    public List<RequestsAdsDTORead> readAll(Sort sort){
        return requestsAdsRepository.findAll(sort).stream().map(requestsAdsMapper::toDtoRead).toList();
    }

    @Override
    public Slice<RequestsAdsDTORead> readAll(Pageable pageParam){
        return requestsAdsRepository.findAll(pageParam).map(requestsAdsMapper::toDtoRead);
    }

    @Override
    public Optional<RequestsAdsDTORead> findById(Integer id){
        return requestsAdsRepository.findById(id).map(requestsAdsMapper::toDtoRead);
    }

    @Override
    public Optional<Object> update(Integer id, RequestsAdsDTOCreate requestAdDTOCreate){
        return requestsAdsRepository.findById(id).map(requestAd -> {
            Object obj = requestsAdsMapper.updateRequestAdFromDtoCreate(requestAdDTOCreate, requestAd);
            if (obj instanceof RequestsAds)
                return requestsAdsMapper.toDtoRead(requestsAdsRepository.save((RequestsAds) obj));
            else
                return obj;
        });
    }

    @Override
    public Boolean deleteById(Integer id){
        if (requestsAdsRepository.existsById(id)){
            requestsAdsRepository.deleteById(id);
            return true;
        } else
            return false;
    }
}

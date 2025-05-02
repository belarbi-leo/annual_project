package ecodeli.DTOService;

import ecodeli.DTO.create.AdsDTOCreate;
import ecodeli.DTO.read.AdsDTORead;
import ecodeli.enumeratation.StatusAdEnum;
import ecodeli.modelMapper.subClassImpl.AdsMapperSubClassImpl;
import ecodeli.modele.Ads;
import ecodeli.repository.AdsRepository;
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
public class AdsDTOServiceImpl implements AdsDTOService {
    private AdsRepository adsRepository;
    @Autowired
    private AdsMapperSubClassImpl adsMapper;

    @Override
    public Object create(AdsDTOCreate adDtoCreate){
        if (adDtoCreate.getStatusAd() == null) adDtoCreate.setStatusAd(StatusAdEnum.pending);
        if (adDtoCreate.getDateCreationAd() == null) adDtoCreate.setDateCreationAd(Timestamp.valueOf(LocalDateTime.now()));

        Object obj = adsMapper.fromDtoCreate(adDtoCreate);
        if (obj instanceof Ads)
            return adsMapper.toDtoRead(adsRepository.save((Ads) obj));
        else
            return obj;
    }

    @Override
    public List<AdsDTORead> readAll(){
        return adsRepository.findAll().stream().map(adsMapper::toDtoRead).toList();
    }

    @Override
    public List<AdsDTORead> readAll(Sort sort){
        return adsRepository.findAll(sort).stream().map(adsMapper::toDtoRead).toList();
    }

    @Override
    public Slice<AdsDTORead> readAll(Pageable pageParam){
        return adsRepository.findAll(pageParam).map(adsMapper::toDtoRead);
    }

    @Override
    public Optional<AdsDTORead> findById(Integer id){
        return adsRepository.findById(id).map(adsMapper::toDtoRead);
    }

    @Override
    public Optional<Object> update(Integer id, AdsDTOCreate adDTOCreate){
        return adsRepository.findById(id).map(ad -> {
            Object obj = adsMapper.updateAdFromDtoCreate(adDTOCreate, ad);
            if (obj instanceof Ads)
                return adsMapper.toDtoRead(adsRepository.save((Ads) obj));
            else
                return obj;
        });
    }

    @Override
    public Boolean deleteById(Integer id){
        if (adsRepository.existsById(id)){
            adsRepository.deleteById(id);
            return true;
        } else
            return false;
    }
}

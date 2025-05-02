package ecodeli.DTOService;

import ecodeli.DTO.create.OpinionsDTOCreate;
import ecodeli.DTO.read.OpinionsDTORead;
import ecodeli.modelMapper.subClassImpl.OpinionsMapperSubClassImpl;
import ecodeli.modele.Opinions;
import ecodeli.repository.OpinionsRepository;
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
public class OpinionsDTOServiceImpl implements OpinionsDTOService {
    private OpinionsRepository opinionsRepository;
    @Autowired
    private OpinionsMapperSubClassImpl opinionsMapper;

    @Override
    public Object create(OpinionsDTOCreate opinionDtoCreate){
        if (opinionDtoCreate.getDateOpinion() == null) opinionDtoCreate.setDateOpinion(Timestamp.valueOf(LocalDateTime.now()));

        Object obj = opinionsMapper.fromDtoCreate(opinionDtoCreate);
        if (obj instanceof Opinions)
            return opinionsMapper.toDtoRead(opinionsRepository.save((Opinions) obj));
        else
            return obj;
    }

    @Override
    public List<OpinionsDTORead> readAll(){
        return opinionsRepository.findAll().stream().map(opinionsMapper::toDtoRead).toList();
    }

    @Override
    public List<OpinionsDTORead> readAll(Sort sort){
        return opinionsRepository.findAll(sort).stream().map(opinionsMapper::toDtoRead).toList();
    }

    @Override
    public Slice<OpinionsDTORead> readAll(Pageable pageParam){
        return opinionsRepository.findAll(pageParam).map(opinionsMapper::toDtoRead);
    }

    @Override
    public Optional<OpinionsDTORead> findById(Integer id){
        return opinionsRepository.findById(id).map(opinionsMapper::toDtoRead);
    }

    @Override
    public Optional<Object> update(Integer id, OpinionsDTOCreate opinionDTOCreate){
        return opinionsRepository.findById(id).map(opinion -> {
            Object obj = opinionsMapper.updateOpinionFromDtoCreate(opinionDTOCreate, opinion);
            if (obj instanceof Opinions)
                return opinionsMapper.toDtoRead(opinionsRepository.save((Opinions) obj));
            else
                return obj;
        });
    }

    @Override
    public Boolean deleteById(Integer id){
        if (opinionsRepository.existsById(id)){
            opinionsRepository.deleteById(id);
            return true;
        } else
            return false;
    }
}

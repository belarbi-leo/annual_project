package ecodeli.DTOService;

import ecodeli.DTO.create.DisputesDTOCreate;
import ecodeli.DTO.read.DisputesDTORead;
import ecodeli.enumeratation.StatusDisputeEnum;
import ecodeli.modelMapper.subClassImpl.DisputesMapperSubClassImpl;
import ecodeli.modele.Disputes;
import ecodeli.repository.DisputesRepository;
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
public class DisputesDTOServiceImpl implements DisputesDTOService {
    private DisputesRepository disputesRepository;
    @Autowired
    private DisputesMapperSubClassImpl disputesMapper;

    @Override
    public Object create(DisputesDTOCreate disputeDtoCreate){
        if (disputeDtoCreate.getDateStatusDispute() == null) disputeDtoCreate.setDateStatusDispute(Timestamp.valueOf(LocalDateTime.now()));
        if (disputeDtoCreate.getStatusDispute() == null) disputeDtoCreate.setStatusDispute(StatusDisputeEnum.pending);

        Object obj = disputesMapper.fromDtoCreate(disputeDtoCreate);
        if (obj instanceof Disputes)
            return disputesMapper.toDtoRead(disputesRepository.save((Disputes) obj));
        else
            return obj;
    }

    @Override
    public List<DisputesDTORead> readAll(){
        return disputesRepository.findAll().stream().map(disputesMapper::toDtoRead).toList();
    }

    @Override
    public List<DisputesDTORead> readAll(Sort sort){
        return disputesRepository.findAll(sort).stream().map(disputesMapper::toDtoRead).toList();
    }

    @Override
    public Slice<DisputesDTORead> readAll(Pageable pageParam){
        return disputesRepository.findAll(pageParam).map(disputesMapper::toDtoRead);
    }

    @Override
    public Optional<DisputesDTORead> findById(Integer id){
        return disputesRepository.findById(id).map(disputesMapper::toDtoRead);
    }

    @Override
    public Optional<Object> update(Integer id, DisputesDTOCreate disputeDTOCreate){
        return disputesRepository.findById(id).map(dispute -> {
            Object obj = disputesMapper.updateDisputeFromDtoCreate(disputeDTOCreate, dispute);
            if (obj instanceof Disputes)
                return disputesMapper.toDtoRead(disputesRepository.save((Disputes) obj));
            else
                return obj;
        });
    }

    @Override
    public Boolean deleteById(Integer id){
        if (disputesRepository.existsById(id)){
            disputesRepository.deleteById(id);
            return true;
        } else
            return false;
    }
}

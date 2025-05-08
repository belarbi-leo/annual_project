package ecodeli.DTOService;

import ecodeli.DTO.create.DepotsDTOCreate;
import ecodeli.DTO.read.DepotsDTORead;
import ecodeli.modelMapper.DepotsMapper;
import ecodeli.repository.DepotsRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DepotsDTOServiceImpl implements DepotsDTOService {
    private DepotsRepository depotsRepository;
    private DepotsMapper depotsMapper;

    @Override
    public DepotsDTORead create(DepotsDTOCreate depotDtoCreate){
        return depotsMapper.toDtoRead(depotsRepository.save(depotsMapper.fromDtoCreate(depotDtoCreate)));
    }

    @Override
    public List<DepotsDTORead> readAll(){
        return depotsRepository.findAll().stream().map(depotsMapper::toDtoRead).toList();
    }

    @Override
    public List<DepotsDTORead> readAll(Sort sort){
        return depotsRepository.findAll(sort).stream().map(depotsMapper::toDtoRead).toList();
    }

    @Override
    public Slice<DepotsDTORead> readAll(Pageable pageParam){
        return depotsRepository.findAll(pageParam).map(depotsMapper::toDtoRead);
    }

    @Override
    public Optional<DepotsDTORead> findById(Integer id){
        return depotsRepository.findById(id).map(depotsMapper::toDtoRead);
    }

    @Override
    public Optional<DepotsDTORead> update(Integer id, DepotsDTOCreate depotDtoCreate){
        return depotsRepository.findById(id).map(depot -> depotsMapper.toDtoRead(depotsRepository.save(depotsMapper.updateDepotFromDtoCreate(depotDtoCreate,depot))));
    }

    @Override
    public Boolean deleteById(Integer id){
        if(depotsRepository.existsById(id)){
            depotsRepository.deleteById(id);
            return true;
        } else
            return false;
    }
}

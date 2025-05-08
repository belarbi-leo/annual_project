package ecodeli.DTOService;

import ecodeli.DTO.create.MaterielsDTOCreate;
import ecodeli.DTO.read.MaterielsDTORead;
import ecodeli.modelMapper.subClassImpl.MaterielsMapperSubClassImpl;
import ecodeli.modele.Materiels;
import ecodeli.repository.MaterielsRepository;
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
public class MaterielsDTOServiceImpl implements MaterielsDTOService {
    private MaterielsRepository materielsRepository;
    @Autowired
    private MaterielsMapperSubClassImpl materielsMapper;

    @Override
    public Object create(MaterielsDTOCreate materielDtoCreate){
        Object obj = materielsMapper.fromDtoCreate(materielDtoCreate);
        if (obj instanceof Materiels)
            return materielsMapper.toDtoRead(materielsRepository.save((Materiels) obj));
        else
            return obj;
    }

    @Override
    public List<MaterielsDTORead> readAll(){
        return materielsRepository.findAll().stream().map(materielsMapper::toDtoRead).toList();
    }

    @Override
    public List<MaterielsDTORead> readAll(Sort sort){
        return materielsRepository.findAll(sort).stream().map(materielsMapper::toDtoRead).toList();
    }

    @Override
    public Slice<MaterielsDTORead> readAll(Pageable pageParam){
        return materielsRepository.findAll(pageParam).map(materielsMapper::toDtoRead);
    }

    @Override
    public Optional<MaterielsDTORead> findById(Integer id){
        return materielsRepository.findById(id).map(materielsMapper::toDtoRead);
    }

    @Override
    public Optional<Object> update(Integer id, MaterielsDTOCreate materielDTOCreate){
        return materielsRepository.findById(id).map(materiel -> {
            Object obj = materielsMapper.updateMaterielFromDtoCreate(materielDTOCreate, materiel);
            if (obj instanceof Materiels)
                return materielsMapper.toDtoRead(materielsRepository.save((Materiels) obj));
            else
                return obj;
        });
    }

    @Override
    public Boolean deleteById(Integer id){
        if (materielsRepository.existsById(id)){
            materielsRepository.deleteById(id);
            return true;
        } else
            return false;
    }
}

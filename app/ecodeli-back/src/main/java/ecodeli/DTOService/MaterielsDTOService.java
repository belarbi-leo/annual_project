package ecodeli.DTOService;

import ecodeli.DTO.create.MaterielsDTOCreate;
import ecodeli.DTO.read.MaterielsDTORead;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface MaterielsDTOService {
    Object create(MaterielsDTOCreate materielDtoCreate);

    List<MaterielsDTORead> readAll();

    List<MaterielsDTORead> readAll(Sort sort);

    Slice<MaterielsDTORead> readAll(Pageable pageParam);

    Optional<MaterielsDTORead> findById(Integer id);

    Optional<Object> update(Integer id, MaterielsDTOCreate materielDTOCreate);

    Boolean deleteById(Integer id);
}

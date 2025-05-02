package ecodeli.DTOService;

import ecodeli.DTO.create.ServicesDocsDTOCreate;
import ecodeli.DTO.read.ServicesDocsDTORead;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ServicesDocsDTOService {
    Object create(ServicesDocsDTOCreate serviceDocDtoCreate);

    List<ServicesDocsDTORead> readAll();

    List<ServicesDocsDTORead> readAll(Sort sort);

    Slice<ServicesDocsDTORead> readAll(Pageable pageParam);

    Optional<ServicesDocsDTORead> findById(Integer id);

    Optional<Object> update(Integer id, ServicesDocsDTOCreate serviceDocDTOCreate);

    Boolean deleteById(Integer id);
}

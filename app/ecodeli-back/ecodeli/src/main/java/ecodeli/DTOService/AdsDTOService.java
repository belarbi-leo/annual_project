package ecodeli.DTOService;

import ecodeli.DTO.create.AdsDTOCreate;
import ecodeli.DTO.read.AdsDTORead;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface AdsDTOService {
    Object create(AdsDTOCreate adDtoCreate);

    List<AdsDTORead> readAll();

    List<AdsDTORead> readAll(Sort sort);

    Slice<AdsDTORead> readAll(Pageable pageParam);

    Optional<AdsDTORead> findById(Integer id);

    Optional<Object> update(Integer id, AdsDTOCreate adDTOCreate);

    Boolean deleteById(Integer id);
}

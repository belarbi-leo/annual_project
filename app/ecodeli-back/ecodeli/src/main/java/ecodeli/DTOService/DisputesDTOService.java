package ecodeli.DTOService;

import ecodeli.DTO.create.DisputesDTOCreate;
import ecodeli.DTO.read.DisputesDTORead;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface DisputesDTOService {
    Object create(DisputesDTOCreate disputeDtoCreate);

    List<DisputesDTORead> readAll();

    List<DisputesDTORead> readAll(Sort sort);

    Slice<DisputesDTORead> readAll(Pageable pageParam);

    Optional<DisputesDTORead> findById(Integer id);

    Optional<Object> update(Integer id, DisputesDTOCreate disputeDTOCreate);

    Boolean deleteById(Integer id);
}

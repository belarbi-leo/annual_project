package ecodeli.DTOService;

import ecodeli.DTO.create.OpinionsDTOCreate;
import ecodeli.DTO.read.OpinionsDTORead;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface OpinionsDTOService {
    Object create(OpinionsDTOCreate opinionDtoCreate);

    List<OpinionsDTORead> readAll();

    List<OpinionsDTORead> readAll(Sort sort);

    Slice<OpinionsDTORead> readAll(Pageable pageParam);

    Optional<OpinionsDTORead> findById(Integer id);

    Optional<Object> update(Integer id, OpinionsDTOCreate opinionDTOCreate);

    Boolean deleteById(Integer id);
}

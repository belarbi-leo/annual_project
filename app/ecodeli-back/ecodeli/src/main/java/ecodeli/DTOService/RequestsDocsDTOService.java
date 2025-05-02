package ecodeli.DTOService;

import ecodeli.DTO.create.RequestsDocsDTOCreate;
import ecodeli.DTO.read.RequestsDocsDTORead;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface RequestsDocsDTOService {
    Object create(RequestsDocsDTOCreate requestDocDtoCreate);

    List<RequestsDocsDTORead> readAll();

    List<RequestsDocsDTORead> readAll(Sort sort);

    Slice<RequestsDocsDTORead> readAll(Pageable pageParam);

    Optional<RequestsDocsDTORead> findById(Integer id);

    Optional<Object> update(Integer id, RequestsDocsDTOCreate requestDocDTOCreate);

    Boolean deleteById(Integer id);
}

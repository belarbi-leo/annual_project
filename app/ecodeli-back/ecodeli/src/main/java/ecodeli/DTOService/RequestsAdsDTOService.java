package ecodeli.DTOService;

import ecodeli.DTO.create.RequestsAdsDTOCreate;
import ecodeli.DTO.read.RequestsAdsDTORead;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface RequestsAdsDTOService {
    Object create(RequestsAdsDTOCreate requestAdDtoCreate);

    List<RequestsAdsDTORead> readAll();

    List<RequestsAdsDTORead> readAll(Sort sort);

    Slice<RequestsAdsDTORead> readAll(Pageable pageParam);

    Optional<RequestsAdsDTORead> findById(Integer id);

    Optional<Object> update(Integer id, RequestsAdsDTOCreate requestAdDTOCreate);

    Boolean deleteById(Integer id);
}

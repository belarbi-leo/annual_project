package ecodeli.DTOService;

import ecodeli.DTO.create.RequestsServicesDTOCreate;
import ecodeli.DTO.read.RequestsServicesDTORead;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface RequestsServicesDTOService {
    Object create(RequestsServicesDTOCreate requestServiceDtoCreate);

    List<RequestsServicesDTORead> readAll();

    List<RequestsServicesDTORead> readAll(Sort sort);

    Slice<RequestsServicesDTORead> readAll(Pageable pageParam);

    Optional<RequestsServicesDTORead> findById(Integer id);

    Optional<Object> update(Integer id, RequestsServicesDTOCreate requestServiceDTOCreate);

    Boolean deleteById(Integer id);
}

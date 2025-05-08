package ecodeli.DTOService;

import ecodeli.DTO.create.RoutesDTOCreate;
import ecodeli.DTO.read.RoutesDTORead;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface RoutesDTOService {
    Object create(RoutesDTOCreate routeDtoCreate);

    List<RoutesDTORead> readAll();

    List<RoutesDTORead> readAll(Sort sort);

    Slice<RoutesDTORead> readAll(Pageable pageParam);

    Optional<RoutesDTORead> findById(Integer id);

    Optional<Object> update(Integer id, RoutesDTOCreate routeDTOCreate);

    Boolean deleteById(Integer id);
}

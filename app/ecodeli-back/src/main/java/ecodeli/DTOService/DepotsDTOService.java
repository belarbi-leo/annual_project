package ecodeli.DTOService;

import ecodeli.DTO.create.DepotsDTOCreate;
import ecodeli.DTO.read.DepotsDTORead;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface DepotsDTOService {
    DepotsDTORead create(DepotsDTOCreate depotDtoCreate);

    List<DepotsDTORead> readAll();

    List<DepotsDTORead> readAll(Sort sort);

    Slice<DepotsDTORead> readAll(Pageable pageParam);

    Optional<DepotsDTORead> findById(Integer id);

    Optional<DepotsDTORead> update(Integer id, DepotsDTOCreate depotDtoCreate);

    Boolean deleteById(Integer id);
}

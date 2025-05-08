package ecodeli.DTOService;

import ecodeli.DTO.create.PackagesDTOCreate;
import ecodeli.DTO.read.PackagesDTORead;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface PackagesDTOService {
    Object create(PackagesDTOCreate packDtoCreate);

    List<PackagesDTORead> readAll();

    List<PackagesDTORead> readAll(Sort sort);

    Slice<PackagesDTORead> readAll(Pageable pageParam);

    Optional<PackagesDTORead> findById(Integer id);

    Optional<Object> update(Integer id, PackagesDTOCreate packDTOCreate);

    Boolean deleteById(Integer id);
}

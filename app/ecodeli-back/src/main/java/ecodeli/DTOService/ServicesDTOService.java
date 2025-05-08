package ecodeli.DTOService;

import ecodeli.DTO.create.ServicesDTOCreate;
import ecodeli.DTO.read.ServicesDTORead;
import ecodeli.enumeratation.AuthorizationSvcEnum;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ServicesDTOService {
    Object create(ServicesDTOCreate serviceDtoCreate);

    List<ServicesDTORead> readAll();

    List<ServicesDTORead> readAll(Sort sort);

    Slice<ServicesDTORead> readAll(Pageable pageParam);

    List<ServicesDTORead> readAllByAuthIn(Set<AuthorizationSvcEnum> auth);

    List<ServicesDTORead> readAllByAuthIn(Set<AuthorizationSvcEnum> auth, Sort sort);

    Slice<ServicesDTORead> readAllByAuthIn(Set<AuthorizationSvcEnum> auth, Pageable pageParam);

    Optional<ServicesDTORead> findById(Integer id);

    Optional<Object> update(Integer id, ServicesDTOCreate serviceDTOCreate);

    Boolean deleteById(Integer id);
}

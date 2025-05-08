package ecodeli.DTOService;

import ecodeli.DTO.create.SubscriptionsDTOCreate;
import ecodeli.DTO.read.SubscriptionsDTORead;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface SubscriptionsDTOService {
    SubscriptionsDTORead create(SubscriptionsDTOCreate subscriptionDtoCreate);

    List<SubscriptionsDTORead> readAll();

    List<SubscriptionsDTORead> readAll(Sort sort);

    Slice<SubscriptionsDTORead> readAll(Pageable pageParam);

    Optional<SubscriptionsDTORead> findById(Integer id);

    Optional<SubscriptionsDTORead> update(Integer id, SubscriptionsDTOCreate subscriptionDtoCreate);

    Boolean deleteById(Integer id);
}

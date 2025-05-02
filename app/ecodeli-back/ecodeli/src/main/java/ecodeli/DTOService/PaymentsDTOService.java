package ecodeli.DTOService;

import ecodeli.DTO.create.PaymentsDTOCreate;
import ecodeli.DTO.read.PaymentsDTORead;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface PaymentsDTOService {
    Object create(PaymentsDTOCreate paymentDtoCreate);

    List<PaymentsDTORead> readAll();

    List<PaymentsDTORead> readAll(Sort sort);

    Slice<PaymentsDTORead> readAll(Pageable pageParam);

    Optional<PaymentsDTORead> findById(Integer id);

    Optional<Object> update(Integer id, PaymentsDTOCreate paymentDTOCreate);

    Boolean deleteById(Integer id);
}

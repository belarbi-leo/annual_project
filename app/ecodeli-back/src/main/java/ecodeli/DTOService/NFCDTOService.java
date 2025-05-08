package ecodeli.DTOService;

import ecodeli.DTO.create.NFCDTOCreate;
import ecodeli.DTO.read.NFCDTORead;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface NFCDTOService {
    Object create(NFCDTOCreate nfcDtoCreate);

    List<NFCDTORead> readAll();

    List<NFCDTORead> readAll(Sort sort);

    Slice<NFCDTORead> readAll(Pageable pageParam);

    Optional<NFCDTORead> findById(Integer id);

    Optional<Object> update(Integer id, NFCDTOCreate nfcDTOCreate);

    Boolean deleteById(Integer id);
}

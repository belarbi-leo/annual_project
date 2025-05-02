package ecodeli.DTOService;

import ecodeli.DTO.create.LanguagesDTOCreate;
import ecodeli.DTO.read.LanguagesDTORead;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface LanguagesDTOService {
    LanguagesDTORead create(LanguagesDTOCreate languageDtoCreate);

    List<LanguagesDTORead> readAll();

    List<LanguagesDTORead> readAll(Sort sort);

    Slice<LanguagesDTORead> readAll(Pageable pageParam);

    Optional<LanguagesDTORead> findById(Integer id);

    Optional<LanguagesDTORead> findByIso(String iso);

    Optional<LanguagesDTORead> update(Integer id, LanguagesDTOCreate languageDtoCreate);

    Optional<LanguagesDTORead> update(String iso, LanguagesDTOCreate languageDtoCreate);

    Boolean deleteById(Integer id);

    Boolean deleteByIso(String iso);
}

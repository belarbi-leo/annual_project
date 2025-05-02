package ecodeli.DTOService;

import ecodeli.DTO.create.UsersDTOCreate;
import ecodeli.DTO.read.UsersDTORead;
import ecodeli.enumeratation.RoleEnum;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface UsersDTOService {
    Object create(UsersDTOCreate userDtoCreate);

    List<UsersDTORead> readAll();

    List<UsersDTORead> readAll(Sort sort);

    Slice<UsersDTORead> readAll(Pageable pageParam);

    List<UsersDTORead> readAllByRole(RoleEnum role);

    List<UsersDTORead> readAllByRole(RoleEnum role, Sort sort);

    Slice<UsersDTORead> readAllByRole(RoleEnum role, Pageable pageParam);

    Optional<UsersDTORead> findById(Integer id);

    /*Optional<UsersDTORead>*/ Optional<Object> update(Integer id, UsersDTOCreate userDTOCreate);

    Boolean deleteById(Integer id);
}

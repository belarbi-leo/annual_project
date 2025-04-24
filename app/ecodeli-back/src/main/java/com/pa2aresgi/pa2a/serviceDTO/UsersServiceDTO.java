package com.pa2aresgi.pa2a.serviceDTO;

import com.pa2aresgi.pa2a.DTO.create.UsersDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.UsersDTORead;
import com.pa2aresgi.pa2a.enumeratation.RoleEnum;
import org.apache.catalina.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface UsersServiceDTO {
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

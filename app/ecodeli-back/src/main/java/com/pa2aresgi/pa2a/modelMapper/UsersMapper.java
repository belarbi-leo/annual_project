package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.UsersDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.UsersDTORead;
import com.pa2aresgi.pa2a.modele.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersMapper {
    public UsersDTORead toDtoRead(Users user);
    public Users fromDtoCreate(UsersDTOCreate userDtoCreate);
}

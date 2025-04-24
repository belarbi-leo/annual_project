package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.RoutesDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.RoutesDTORead;
import com.pa2aresgi.pa2a.modele.Routes;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoutesMapper {
    public RoutesDTORead toDtoRead(Routes route);
    public Routes fromDtoCreate(RoutesDTOCreate routeDtoCreate);
}

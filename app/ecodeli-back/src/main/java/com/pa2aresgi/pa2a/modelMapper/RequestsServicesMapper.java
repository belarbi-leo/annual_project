package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.RequestsServicesDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.RequestsServicesDTORead;
import com.pa2aresgi.pa2a.modele.RequestsServices;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RequestsServicesMapper {
    public RequestsServicesDTORead toDtoRead(RequestsServices requestService);
    public RequestsServices fromDtoCreate(RequestsServicesDTOCreate requestServiceDtoCreate);
}

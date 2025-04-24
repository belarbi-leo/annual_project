package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.ServicesDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.ServicesDTORead;
import com.pa2aresgi.pa2a.modele.Services;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServicesMapper {
    public ServicesDTORead toDtoRead(Services service);
    public Services fromDtoCreate(ServicesDTOCreate serviceDtoCreate);
}

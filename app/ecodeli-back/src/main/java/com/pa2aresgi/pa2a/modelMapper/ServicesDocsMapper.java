package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.ServicesDocsDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.ServicesDocsDTORead;
import com.pa2aresgi.pa2a.modele.ServicesDocs;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServicesDocsMapper {
    public ServicesDocsDTORead toDtoRead(ServicesDocs serviceDoc);
    public ServicesDocs fromDtoCreate(ServicesDocsDTOCreate serviceDocDtoCreate);
}

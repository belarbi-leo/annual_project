package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.RequestsDocsDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.RequestsDocsDTORead;
import com.pa2aresgi.pa2a.modele.RequestsDocs;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RequestsDocsMapper {
    public RequestsDocsDTORead toDtoRead(RequestsDocs requestDoc);
    public RequestsDocs fromDtoCreate(RequestsDocsDTOCreate requestDocDtoCreate);
}

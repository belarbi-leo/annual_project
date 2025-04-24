package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.OpinionsDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.OpinionsDTORead;
import com.pa2aresgi.pa2a.modele.Opinions;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OpinionsMapper {
    public OpinionsDTORead toDtoRead(Opinions opinion);
    public Opinions fromDtoCreate(OpinionsDTOCreate opinionDtoCreate);
}

package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.AdsDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.AdsDTORead;
import com.pa2aresgi.pa2a.modele.Ads;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdsMapper {
    public AdsDTORead toDtoRead(Ads ad);
    public Ads fromDtoCreate(AdsDTOCreate adDtoCreate);
}

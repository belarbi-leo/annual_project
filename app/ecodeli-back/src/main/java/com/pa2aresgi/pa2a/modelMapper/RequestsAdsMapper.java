package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.RequestsAdsDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.RequestsAdsDTORead;
import com.pa2aresgi.pa2a.modele.RequestsAds;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RequestsAdsMapper {
    public RequestsAdsDTORead toDtoRead(RequestsAds requestAd);
    public RequestsAds fromDtoCreate(RequestsAdsDTOCreate requestAdDtoCreate);
}

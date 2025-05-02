package ecodeli.modelMapper;

import ecodeli.DTO.create.RequestsAdsDTOCreate;
import ecodeli.DTO.read.RequestsAdsDTORead;
import ecodeli.modele.RequestsAds;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RequestsAdsMapper {
    public RequestsAdsDTORead toDtoRead(RequestsAds requestAd);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "ad", ignore = true)
    public RequestsAds fromDtoCreate(RequestsAdsDTOCreate requestAdDtoCreate);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "ad", ignore = true)
    public RequestsAds updateRequestAdFromDtoCreate(RequestsAdsDTOCreate requestAdDtoCreate, @MappingTarget RequestsAds requestAd);
}

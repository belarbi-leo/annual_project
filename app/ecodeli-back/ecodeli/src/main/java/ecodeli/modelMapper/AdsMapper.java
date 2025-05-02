package ecodeli.modelMapper;

import ecodeli.DTO.create.AdsDTOCreate;
import ecodeli.DTO.read.AdsDTORead;
import ecodeli.modele.Ads;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AdsMapper {
    public AdsDTORead toDtoRead(Ads ad);

    @Mapping(target = "userCreator", ignore = true)
    @Mapping(target = "userAccept", ignore = true)
    @Mapping(target = "svc", ignore = true)
    public Ads fromDtoCreate(AdsDTOCreate adDtoCreate);

    @Mapping(target = "userCreator", ignore = true)
    @Mapping(target = "userAccept", ignore = true)
    @Mapping(target = "svc", ignore = true)
    public Ads updateAdFromDtoCreate(AdsDTOCreate adDtoCreate, @MappingTarget Ads ad);
}

package ecodeli.modelMapper;

import ecodeli.DTO.create.AdsDTOCreate;
import ecodeli.DTO.read.AdsDTORead;
import ecodeli.modele.Ads;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE/*ReportingPolicy.ERROR*/)
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

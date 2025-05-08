package ecodeli.modelMapper;

import ecodeli.DTO.create.RoutesDTOCreate;
import ecodeli.DTO.read.RoutesDTORead;
import ecodeli.modele.Routes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoutesMapper {
    public RoutesDTORead toDtoRead(Routes route);

    @Mapping(target = "user", ignore = true)
    public Routes fromDtoCreate(RoutesDTOCreate routeDtoCreate);

    @Mapping(target = "user", ignore = true)
    public Routes updateRouteFromDtoCreate(RoutesDTOCreate routeDtoCreate, @MappingTarget Routes route);
}

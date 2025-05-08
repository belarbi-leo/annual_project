package ecodeli.modelMapper;

import ecodeli.DTO.create.ServicesDTOCreate;
import ecodeli.DTO.read.ServicesDTORead;
import ecodeli.modele.Services;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ServicesMapper {
    public ServicesDTORead toDtoRead(Services service);

    @Mapping(target = "adminCreator", ignore = true)
    public Services fromDtoCreate(ServicesDTOCreate serviceDtoCreate);

    @Mapping(target = "adminCreator", ignore = true)
    public Services updateServiceFromDtoCreate(ServicesDTOCreate serviceDtoCreate, @MappingTarget Services service);
}

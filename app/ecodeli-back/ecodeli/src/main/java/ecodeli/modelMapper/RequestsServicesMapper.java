package ecodeli.modelMapper;

import ecodeli.DTO.create.RequestsServicesDTOCreate;
import ecodeli.DTO.read.RequestsServicesDTORead;
import ecodeli.modele.RequestsServices;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RequestsServicesMapper {
    public RequestsServicesDTORead toDtoRead(RequestsServices requestService);

    @Mapping(target = "userReq", ignore = true)
    @Mapping(target = "adminRes", ignore = true)
    @Mapping(target = "svc", ignore = true)
    public RequestsServices fromDtoCreate(RequestsServicesDTOCreate requestServiceDtoCreate);

    @Mapping(target = "userReq", ignore = true)
    @Mapping(target = "adminRes", ignore = true)
    @Mapping(target = "svc", ignore = true)
    public RequestsServices updateRequestServiceFromDtoCreate(RequestsServicesDTOCreate requestServiceDtoCreate, @MappingTarget RequestsServices requestService);
}

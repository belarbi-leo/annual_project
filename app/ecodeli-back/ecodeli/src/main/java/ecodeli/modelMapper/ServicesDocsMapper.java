package ecodeli.modelMapper;

import ecodeli.DTO.create.ServicesDocsDTOCreate;
import ecodeli.DTO.read.ServicesDocsDTORead;
import ecodeli.modele.ServicesDocs;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ServicesDocsMapper {
    public ServicesDocsDTORead toDtoRead(ServicesDocs serviceDoc);

    @Mapping(target = "svc", ignore = true)
    public ServicesDocs fromDtoCreate(ServicesDocsDTOCreate serviceDocDtoCreate);

    @Mapping(target = "svc", ignore = true)
    public ServicesDocs updateServiceDocfromDtoCreate(ServicesDocsDTOCreate serviceDocDTOCreate, @MappingTarget ServicesDocs serviceDoc);
}

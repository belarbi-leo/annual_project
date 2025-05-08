package ecodeli.modelMapper;

import ecodeli.DTO.create.RequestsDocsDTOCreate;
import ecodeli.DTO.read.RequestsDocsDTORead;
import ecodeli.modele.RequestsDocs;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RequestsDocsMapper {
    public RequestsDocsDTORead toDtoRead(RequestsDocs requestDoc);

    @Mapping(target = "reqSvc", ignore = true)
    public RequestsDocs fromDtoCreate(RequestsDocsDTOCreate requestDocDtoCreate);

    @Mapping(target = "reqSvc", ignore = true)
    public RequestsDocs updateRequestDocFromDtoCreate(RequestsDocsDTOCreate requestDocDtoCreate, @MappingTarget RequestsDocs requestDoc);
}

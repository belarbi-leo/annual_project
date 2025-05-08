package ecodeli.modelMapper;

import ecodeli.DTO.create.LanguagesDTOCreate;
import ecodeli.DTO.read.LanguagesDTORead;
import ecodeli.modele.Languages;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LanguagesMapper {
    public LanguagesDTORead toDtoRead(Languages language);
    public Languages fromDtoCreate(LanguagesDTOCreate languageDtoCreate);
    public Languages updateDtoCreate(LanguagesDTOCreate languageDtoCreate, @MappingTarget Languages language);
}

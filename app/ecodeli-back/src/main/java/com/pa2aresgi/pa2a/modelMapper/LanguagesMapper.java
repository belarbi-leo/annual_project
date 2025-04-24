package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.LanguagesDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.LanguagesDTORead;
import com.pa2aresgi.pa2a.modele.Languages;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LanguagesMapper {
    public LanguagesDTORead toDtoRead(Languages language);
    public Languages fromDtoCreate(LanguagesDTOCreate languageDtoCreate);
}

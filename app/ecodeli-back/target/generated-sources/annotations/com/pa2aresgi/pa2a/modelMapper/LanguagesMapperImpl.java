package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.LanguagesDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.LanguagesDTORead;
import com.pa2aresgi.pa2a.modele.Languages;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-24T16:10:09+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class LanguagesMapperImpl implements LanguagesMapper {

    @Override
    public LanguagesDTORead toDtoRead(Languages language) {
        if ( language == null ) {
            return null;
        }

        LanguagesDTORead languagesDTORead = new LanguagesDTORead();

        languagesDTORead.setIdLanguage( language.getIdLanguage() );
        languagesDTORead.setName( language.getName() );
        languagesDTORead.setIso( language.getIso() );
        languagesDTORead.setAvailable( language.getAvailable() );

        return languagesDTORead;
    }

    @Override
    public Languages fromDtoCreate(LanguagesDTOCreate languageDtoCreate) {
        if ( languageDtoCreate == null ) {
            return null;
        }

        Languages languages = new Languages();

        languages.setName( languageDtoCreate.getName() );
        languages.setIso( languageDtoCreate.getIso() );
        languages.setAvailable( languageDtoCreate.getAvailable() );

        return languages;
    }
}

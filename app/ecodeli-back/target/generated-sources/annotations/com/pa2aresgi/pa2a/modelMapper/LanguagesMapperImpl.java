package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.LanguagesDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.LanguagesDTORead;
import com.pa2aresgi.pa2a.modele.Languages;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-24T16:32:19+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class LanguagesMapperImpl implements LanguagesMapper {

    @Override
    public LanguagesDTORead toDtoRead(Languages language) {
        if ( language == null ) {
            return null;
        }

        LanguagesDTORead languagesDTORead = new LanguagesDTORead();

        languagesDTORead.setAvailable( language.getAvailable() );
        languagesDTORead.setIdLanguage( language.getIdLanguage() );
        languagesDTORead.setIso( language.getIso() );
        languagesDTORead.setName( language.getName() );

        return languagesDTORead;
    }

    @Override
    public Languages fromDtoCreate(LanguagesDTOCreate languageDtoCreate) {
        if ( languageDtoCreate == null ) {
            return null;
        }

        Languages languages = new Languages();

        languages.setAvailable( languageDtoCreate.getAvailable() );
        languages.setIso( languageDtoCreate.getIso() );
        languages.setName( languageDtoCreate.getName() );

        return languages;
    }
}

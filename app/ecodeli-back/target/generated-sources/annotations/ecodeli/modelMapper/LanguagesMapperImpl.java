package ecodeli.modelMapper;

import ecodeli.DTO.create.LanguagesDTOCreate;
import ecodeli.DTO.read.LanguagesDTORead;
import ecodeli.modele.Languages;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-08T15:25:52+0200",
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

    @Override
    public Languages updateDtoCreate(LanguagesDTOCreate languageDtoCreate, Languages language) {
        if ( languageDtoCreate == null ) {
            return language;
        }

        if ( languageDtoCreate.getAvailable() != null ) {
            language.setAvailable( languageDtoCreate.getAvailable() );
        }
        if ( languageDtoCreate.getIso() != null ) {
            language.setIso( languageDtoCreate.getIso() );
        }
        if ( languageDtoCreate.getName() != null ) {
            language.setName( languageDtoCreate.getName() );
        }

        return language;
    }
}

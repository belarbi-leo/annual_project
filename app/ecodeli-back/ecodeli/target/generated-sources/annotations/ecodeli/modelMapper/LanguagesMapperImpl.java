package ecodeli.modelMapper;

import ecodeli.DTO.create.LanguagesDTOCreate;
import ecodeli.DTO.read.LanguagesDTORead;
import ecodeli.modele.Languages;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-07T13:42:24+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.2 (Homebrew)"
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

    @Override
    public Languages updateDtoCreate(LanguagesDTOCreate languageDtoCreate, Languages language) {
        if ( languageDtoCreate == null ) {
            return language;
        }

        if ( languageDtoCreate.getName() != null ) {
            language.setName( languageDtoCreate.getName() );
        }
        if ( languageDtoCreate.getIso() != null ) {
            language.setIso( languageDtoCreate.getIso() );
        }
        if ( languageDtoCreate.getAvailable() != null ) {
            language.setAvailable( languageDtoCreate.getAvailable() );
        }

        return language;
    }
}

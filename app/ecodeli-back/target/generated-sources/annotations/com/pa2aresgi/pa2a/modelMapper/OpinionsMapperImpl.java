package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.OpinionsDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.OpinionsDTORead;
import com.pa2aresgi.pa2a.modele.Opinions;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-24T16:32:19+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class OpinionsMapperImpl implements OpinionsMapper {

    @Override
    public OpinionsDTORead toDtoRead(Opinions opinion) {
        if ( opinion == null ) {
            return null;
        }

        OpinionsDTORead opinionsDTORead = new OpinionsDTORead();

        opinionsDTORead.setAd( opinion.getAd() );
        opinionsDTORead.setDateOpinion( opinion.getDateOpinion() );
        opinionsDTORead.setDescriptionOpinion( opinion.getDescriptionOpinion() );
        opinionsDTORead.setIdOpinion( opinion.getIdOpinion() );
        opinionsDTORead.setNoteOpinion( opinion.getNoteOpinion() );
        opinionsDTORead.setTitleOpinion( opinion.getTitleOpinion() );

        return opinionsDTORead;
    }

    @Override
    public Opinions fromDtoCreate(OpinionsDTOCreate opinionDtoCreate) {
        if ( opinionDtoCreate == null ) {
            return null;
        }

        Opinions opinions = new Opinions();

        opinions.setAd( opinionDtoCreate.getAd() );
        opinions.setDateOpinion( opinionDtoCreate.getDateOpinion() );
        opinions.setDescriptionOpinion( opinionDtoCreate.getDescriptionOpinion() );
        opinions.setNoteOpinion( opinionDtoCreate.getNoteOpinion() );
        opinions.setTitleOpinion( opinionDtoCreate.getTitleOpinion() );

        return opinions;
    }
}

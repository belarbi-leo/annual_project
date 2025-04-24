package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.OpinionsDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.OpinionsDTORead;
import com.pa2aresgi.pa2a.modele.Opinions;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-24T16:10:09+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class OpinionsMapperImpl implements OpinionsMapper {

    @Override
    public OpinionsDTORead toDtoRead(Opinions opinion) {
        if ( opinion == null ) {
            return null;
        }

        OpinionsDTORead opinionsDTORead = new OpinionsDTORead();

        opinionsDTORead.setIdOpinion( opinion.getIdOpinion() );
        opinionsDTORead.setAd( opinion.getAd() );
        opinionsDTORead.setNoteOpinion( opinion.getNoteOpinion() );
        opinionsDTORead.setTitleOpinion( opinion.getTitleOpinion() );
        opinionsDTORead.setDescriptionOpinion( opinion.getDescriptionOpinion() );
        opinionsDTORead.setDateOpinion( opinion.getDateOpinion() );

        return opinionsDTORead;
    }

    @Override
    public Opinions fromDtoCreate(OpinionsDTOCreate opinionDtoCreate) {
        if ( opinionDtoCreate == null ) {
            return null;
        }

        Opinions opinions = new Opinions();

        opinions.setAd( opinionDtoCreate.getAd() );
        opinions.setNoteOpinion( opinionDtoCreate.getNoteOpinion() );
        opinions.setTitleOpinion( opinionDtoCreate.getTitleOpinion() );
        opinions.setDescriptionOpinion( opinionDtoCreate.getDescriptionOpinion() );
        opinions.setDateOpinion( opinionDtoCreate.getDateOpinion() );

        return opinions;
    }
}

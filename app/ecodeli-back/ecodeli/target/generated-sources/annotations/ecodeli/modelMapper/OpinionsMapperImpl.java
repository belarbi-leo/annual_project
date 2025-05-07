package ecodeli.modelMapper;

import ecodeli.DTO.create.OpinionsDTOCreate;
import ecodeli.DTO.read.OpinionsDTORead;
import ecodeli.modele.Opinions;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-07T13:42:24+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.2 (Homebrew)"
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

        opinions.setNoteOpinion( opinionDtoCreate.getNoteOpinion() );
        opinions.setTitleOpinion( opinionDtoCreate.getTitleOpinion() );
        opinions.setDescriptionOpinion( opinionDtoCreate.getDescriptionOpinion() );
        opinions.setDateOpinion( opinionDtoCreate.getDateOpinion() );

        return opinions;
    }

    @Override
    public Opinions updateOpinionFromDtoCreate(OpinionsDTOCreate opinionDtoCreate, Opinions opinion) {
        if ( opinionDtoCreate == null ) {
            return opinion;
        }

        opinion.setNoteOpinion( opinionDtoCreate.getNoteOpinion() );
        if ( opinionDtoCreate.getTitleOpinion() != null ) {
            opinion.setTitleOpinion( opinionDtoCreate.getTitleOpinion() );
        }
        if ( opinionDtoCreate.getDescriptionOpinion() != null ) {
            opinion.setDescriptionOpinion( opinionDtoCreate.getDescriptionOpinion() );
        }
        if ( opinionDtoCreate.getDateOpinion() != null ) {
            opinion.setDateOpinion( opinionDtoCreate.getDateOpinion() );
        }

        return opinion;
    }
}

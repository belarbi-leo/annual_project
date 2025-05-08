package ecodeli.modelMapper;

import ecodeli.DTO.create.OpinionsDTOCreate;
import ecodeli.DTO.read.OpinionsDTORead;
import ecodeli.modele.Opinions;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-08T15:25:52+0200",
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

        opinions.setDateOpinion( opinionDtoCreate.getDateOpinion() );
        opinions.setDescriptionOpinion( opinionDtoCreate.getDescriptionOpinion() );
        opinions.setNoteOpinion( opinionDtoCreate.getNoteOpinion() );
        opinions.setTitleOpinion( opinionDtoCreate.getTitleOpinion() );

        return opinions;
    }

    @Override
    public Opinions updateOpinionFromDtoCreate(OpinionsDTOCreate opinionDtoCreate, Opinions opinion) {
        if ( opinionDtoCreate == null ) {
            return opinion;
        }

        if ( opinionDtoCreate.getDateOpinion() != null ) {
            opinion.setDateOpinion( opinionDtoCreate.getDateOpinion() );
        }
        if ( opinionDtoCreate.getDescriptionOpinion() != null ) {
            opinion.setDescriptionOpinion( opinionDtoCreate.getDescriptionOpinion() );
        }
        opinion.setNoteOpinion( opinionDtoCreate.getNoteOpinion() );
        if ( opinionDtoCreate.getTitleOpinion() != null ) {
            opinion.setTitleOpinion( opinionDtoCreate.getTitleOpinion() );
        }

        return opinion;
    }
}

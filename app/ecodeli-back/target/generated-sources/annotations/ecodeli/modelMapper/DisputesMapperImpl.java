package ecodeli.modelMapper;

import ecodeli.DTO.create.DisputesDTOCreate;
import ecodeli.DTO.read.DisputesDTORead;
import ecodeli.modele.Disputes;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-08T14:19:42+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.2 (Homebrew)"
)
@Component
public class DisputesMapperImpl implements DisputesMapper {

    @Override
    public DisputesDTORead toDtoRead(Disputes dispute) {
        if ( dispute == null ) {
            return null;
        }

        DisputesDTORead disputesDTORead = new DisputesDTORead();

        disputesDTORead.setIdDispute( dispute.getIdDispute() );
        disputesDTORead.setAd( dispute.getAd() );
        disputesDTORead.setUser( dispute.getUser() );
        disputesDTORead.setDateStatusDispute( dispute.getDateStatusDispute() );
        disputesDTORead.setStatusDispute( dispute.getStatusDispute() );
        disputesDTORead.setDescriptionDispute( dispute.getDescriptionDispute() );
        disputesDTORead.setDateEndDispute( dispute.getDateEndDispute() );
        disputesDTORead.setPhotoDispute( dispute.getPhotoDispute() );
        disputesDTORead.setResolutionText( dispute.getResolutionText() );

        return disputesDTORead;
    }

    @Override
    public Disputes fromDtoCreate(DisputesDTOCreate disputeDtoCreate) {
        if ( disputeDtoCreate == null ) {
            return null;
        }

        Disputes disputes = new Disputes();

        disputes.setDateStatusDispute( disputeDtoCreate.getDateStatusDispute() );
        disputes.setStatusDispute( disputeDtoCreate.getStatusDispute() );
        disputes.setDescriptionDispute( disputeDtoCreate.getDescriptionDispute() );
        disputes.setDateEndDispute( disputeDtoCreate.getDateEndDispute() );
        disputes.setPhotoDispute( disputeDtoCreate.getPhotoDispute() );
        disputes.setResolutionText( disputeDtoCreate.getResolutionText() );

        return disputes;
    }

    @Override
    public Disputes updateDisputeFromDtoCreate(DisputesDTOCreate disputeDtoCreate, Disputes dispute) {
        if ( disputeDtoCreate == null ) {
            return dispute;
        }

        if ( disputeDtoCreate.getDateStatusDispute() != null ) {
            dispute.setDateStatusDispute( disputeDtoCreate.getDateStatusDispute() );
        }
        if ( disputeDtoCreate.getStatusDispute() != null ) {
            dispute.setStatusDispute( disputeDtoCreate.getStatusDispute() );
        }
        if ( disputeDtoCreate.getDescriptionDispute() != null ) {
            dispute.setDescriptionDispute( disputeDtoCreate.getDescriptionDispute() );
        }
        if ( disputeDtoCreate.getDateEndDispute() != null ) {
            dispute.setDateEndDispute( disputeDtoCreate.getDateEndDispute() );
        }
        if ( disputeDtoCreate.getPhotoDispute() != null ) {
            dispute.setPhotoDispute( disputeDtoCreate.getPhotoDispute() );
        }
        if ( disputeDtoCreate.getResolutionText() != null ) {
            dispute.setResolutionText( disputeDtoCreate.getResolutionText() );
        }

        return dispute;
    }
}

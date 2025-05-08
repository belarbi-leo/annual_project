package ecodeli.modelMapper;

import ecodeli.DTO.create.DisputesDTOCreate;
import ecodeli.DTO.read.DisputesDTORead;
import ecodeli.modele.Disputes;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-08T15:25:52+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class DisputesMapperImpl implements DisputesMapper {

    @Override
    public DisputesDTORead toDtoRead(Disputes dispute) {
        if ( dispute == null ) {
            return null;
        }

        DisputesDTORead disputesDTORead = new DisputesDTORead();

        disputesDTORead.setAd( dispute.getAd() );
        disputesDTORead.setDateEndDispute( dispute.getDateEndDispute() );
        disputesDTORead.setDateStatusDispute( dispute.getDateStatusDispute() );
        disputesDTORead.setDescriptionDispute( dispute.getDescriptionDispute() );
        disputesDTORead.setIdDispute( dispute.getIdDispute() );
        disputesDTORead.setPhotoDispute( dispute.getPhotoDispute() );
        disputesDTORead.setResolutionText( dispute.getResolutionText() );
        disputesDTORead.setStatusDispute( dispute.getStatusDispute() );
        disputesDTORead.setUser( dispute.getUser() );

        return disputesDTORead;
    }

    @Override
    public Disputes fromDtoCreate(DisputesDTOCreate disputeDtoCreate) {
        if ( disputeDtoCreate == null ) {
            return null;
        }

        Disputes disputes = new Disputes();

        disputes.setDateEndDispute( disputeDtoCreate.getDateEndDispute() );
        disputes.setDateStatusDispute( disputeDtoCreate.getDateStatusDispute() );
        disputes.setDescriptionDispute( disputeDtoCreate.getDescriptionDispute() );
        disputes.setPhotoDispute( disputeDtoCreate.getPhotoDispute() );
        disputes.setResolutionText( disputeDtoCreate.getResolutionText() );
        disputes.setStatusDispute( disputeDtoCreate.getStatusDispute() );

        return disputes;
    }

    @Override
    public Disputes updateDisputeFromDtoCreate(DisputesDTOCreate disputeDtoCreate, Disputes dispute) {
        if ( disputeDtoCreate == null ) {
            return dispute;
        }

        if ( disputeDtoCreate.getDateEndDispute() != null ) {
            dispute.setDateEndDispute( disputeDtoCreate.getDateEndDispute() );
        }
        if ( disputeDtoCreate.getDateStatusDispute() != null ) {
            dispute.setDateStatusDispute( disputeDtoCreate.getDateStatusDispute() );
        }
        if ( disputeDtoCreate.getDescriptionDispute() != null ) {
            dispute.setDescriptionDispute( disputeDtoCreate.getDescriptionDispute() );
        }
        if ( disputeDtoCreate.getPhotoDispute() != null ) {
            dispute.setPhotoDispute( disputeDtoCreate.getPhotoDispute() );
        }
        if ( disputeDtoCreate.getResolutionText() != null ) {
            dispute.setResolutionText( disputeDtoCreate.getResolutionText() );
        }
        if ( disputeDtoCreate.getStatusDispute() != null ) {
            dispute.setStatusDispute( disputeDtoCreate.getStatusDispute() );
        }

        return dispute;
    }
}

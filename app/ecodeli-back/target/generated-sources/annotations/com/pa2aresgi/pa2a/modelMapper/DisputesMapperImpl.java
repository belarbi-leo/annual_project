package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.DisputesDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.DisputesDTORead;
import com.pa2aresgi.pa2a.modele.Disputes;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-24T16:32:19+0200",
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

        disputes.setAd( disputeDtoCreate.getAd() );
        disputes.setDateEndDispute( disputeDtoCreate.getDateEndDispute() );
        disputes.setDateStatusDispute( disputeDtoCreate.getDateStatusDispute() );
        disputes.setDescriptionDispute( disputeDtoCreate.getDescriptionDispute() );
        disputes.setPhotoDispute( disputeDtoCreate.getPhotoDispute() );
        disputes.setResolutionText( disputeDtoCreate.getResolutionText() );
        disputes.setStatusDispute( disputeDtoCreate.getStatusDispute() );
        disputes.setUser( disputeDtoCreate.getUser() );

        return disputes;
    }
}

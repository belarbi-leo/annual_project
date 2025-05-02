package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.DisputesDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.DisputesDTORead;
import com.pa2aresgi.pa2a.modele.Disputes;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-28T23:10:07+0200",
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

        disputes.setAd( disputeDtoCreate.getAd() );
        disputes.setUser( disputeDtoCreate.getUser() );
        disputes.setDateStatusDispute( disputeDtoCreate.getDateStatusDispute() );
        disputes.setStatusDispute( disputeDtoCreate.getStatusDispute() );
        disputes.setDescriptionDispute( disputeDtoCreate.getDescriptionDispute() );
        disputes.setDateEndDispute( disputeDtoCreate.getDateEndDispute() );
        disputes.setPhotoDispute( disputeDtoCreate.getPhotoDispute() );
        disputes.setResolutionText( disputeDtoCreate.getResolutionText() );

        return disputes;
    }
}

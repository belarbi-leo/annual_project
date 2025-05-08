package ecodeli.modelMapper;

import ecodeli.DTO.create.RequestsDocsDTOCreate;
import ecodeli.DTO.read.RequestsDocsDTORead;
import ecodeli.modele.RequestsDocs;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-08T15:25:52+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class RequestsDocsMapperImpl implements RequestsDocsMapper {

    @Override
    public RequestsDocsDTORead toDtoRead(RequestsDocs requestDoc) {
        if ( requestDoc == null ) {
            return null;
        }

        RequestsDocsDTORead requestsDocsDTORead = new RequestsDocsDTORead();

        requestsDocsDTORead.setComment( requestDoc.getComment() );
        requestsDocsDTORead.setDocReq( requestDoc.getDocReq() );
        requestsDocsDTORead.setIdDocReq( requestDoc.getIdDocReq() );
        requestsDocsDTORead.setReqSvc( requestDoc.getReqSvc() );

        return requestsDocsDTORead;
    }

    @Override
    public RequestsDocs fromDtoCreate(RequestsDocsDTOCreate requestDocDtoCreate) {
        if ( requestDocDtoCreate == null ) {
            return null;
        }

        RequestsDocs requestsDocs = new RequestsDocs();

        requestsDocs.setComment( requestDocDtoCreate.getComment() );
        requestsDocs.setDocReq( requestDocDtoCreate.getDocReq() );

        return requestsDocs;
    }

    @Override
    public RequestsDocs updateRequestDocFromDtoCreate(RequestsDocsDTOCreate requestDocDtoCreate, RequestsDocs requestDoc) {
        if ( requestDocDtoCreate == null ) {
            return requestDoc;
        }

        if ( requestDocDtoCreate.getComment() != null ) {
            requestDoc.setComment( requestDocDtoCreate.getComment() );
        }
        if ( requestDocDtoCreate.getDocReq() != null ) {
            requestDoc.setDocReq( requestDocDtoCreate.getDocReq() );
        }

        return requestDoc;
    }
}

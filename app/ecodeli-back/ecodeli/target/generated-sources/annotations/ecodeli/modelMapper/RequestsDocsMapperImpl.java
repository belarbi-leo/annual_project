package ecodeli.modelMapper;

import ecodeli.DTO.create.RequestsDocsDTOCreate;
import ecodeli.DTO.read.RequestsDocsDTORead;
import ecodeli.modele.RequestsDocs;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-07T13:42:24+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.2 (Homebrew)"
)
@Component
public class RequestsDocsMapperImpl implements RequestsDocsMapper {

    @Override
    public RequestsDocsDTORead toDtoRead(RequestsDocs requestDoc) {
        if ( requestDoc == null ) {
            return null;
        }

        RequestsDocsDTORead requestsDocsDTORead = new RequestsDocsDTORead();

        requestsDocsDTORead.setIdDocReq( requestDoc.getIdDocReq() );
        requestsDocsDTORead.setReqSvc( requestDoc.getReqSvc() );
        requestsDocsDTORead.setDocReq( requestDoc.getDocReq() );
        requestsDocsDTORead.setComment( requestDoc.getComment() );

        return requestsDocsDTORead;
    }

    @Override
    public RequestsDocs fromDtoCreate(RequestsDocsDTOCreate requestDocDtoCreate) {
        if ( requestDocDtoCreate == null ) {
            return null;
        }

        RequestsDocs requestsDocs = new RequestsDocs();

        requestsDocs.setDocReq( requestDocDtoCreate.getDocReq() );
        requestsDocs.setComment( requestDocDtoCreate.getComment() );

        return requestsDocs;
    }

    @Override
    public RequestsDocs updateRequestDocFromDtoCreate(RequestsDocsDTOCreate requestDocDtoCreate, RequestsDocs requestDoc) {
        if ( requestDocDtoCreate == null ) {
            return requestDoc;
        }

        if ( requestDocDtoCreate.getDocReq() != null ) {
            requestDoc.setDocReq( requestDocDtoCreate.getDocReq() );
        }
        if ( requestDocDtoCreate.getComment() != null ) {
            requestDoc.setComment( requestDocDtoCreate.getComment() );
        }

        return requestDoc;
    }
}

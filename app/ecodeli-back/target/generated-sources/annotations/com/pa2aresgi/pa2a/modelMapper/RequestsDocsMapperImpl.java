package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.RequestsDocsDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.RequestsDocsDTORead;
import com.pa2aresgi.pa2a.modele.RequestsDocs;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-02T10:08:36+0200",
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

        requestsDocs.setReqSvc( requestDocDtoCreate.getReqSvc() );
        requestsDocs.setDocReq( requestDocDtoCreate.getDocReq() );
        requestsDocs.setComment( requestDocDtoCreate.getComment() );

        return requestsDocs;
    }
}

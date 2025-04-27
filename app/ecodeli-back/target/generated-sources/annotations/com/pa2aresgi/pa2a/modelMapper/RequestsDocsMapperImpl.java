package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.RequestsDocsDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.RequestsDocsDTORead;
import com.pa2aresgi.pa2a.modele.RequestsDocs;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-25T10:18:00+0200",
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
        requestsDocs.setReqSvc( requestDocDtoCreate.getReqSvc() );

        return requestsDocs;
    }
}

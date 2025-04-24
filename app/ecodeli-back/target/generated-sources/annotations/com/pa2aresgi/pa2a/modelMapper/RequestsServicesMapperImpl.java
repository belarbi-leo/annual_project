package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.RequestsServicesDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.RequestsServicesDTORead;
import com.pa2aresgi.pa2a.modele.RequestsServices;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-24T16:10:09+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class RequestsServicesMapperImpl implements RequestsServicesMapper {

    @Override
    public RequestsServicesDTORead toDtoRead(RequestsServices requestService) {
        if ( requestService == null ) {
            return null;
        }

        RequestsServicesDTORead requestsServicesDTORead = new RequestsServicesDTORead();

        requestsServicesDTORead.setIdReqSvc( requestService.getIdReqSvc() );
        requestsServicesDTORead.setUserReq( requestService.getUserReq() );
        requestsServicesDTORead.setAdminRes( requestService.getAdminRes() );
        requestsServicesDTORead.setSvc( requestService.getSvc() );
        requestsServicesDTORead.setStatusReq( requestService.getStatusReq() );
        requestsServicesDTORead.setDateReq( requestService.getDateReq() );
        requestsServicesDTORead.setDateRes( requestService.getDateRes() );
        requestsServicesDTORead.setReasonRes( requestService.getReasonRes() );

        return requestsServicesDTORead;
    }

    @Override
    public RequestsServices fromDtoCreate(RequestsServicesDTOCreate requestServiceDtoCreate) {
        if ( requestServiceDtoCreate == null ) {
            return null;
        }

        RequestsServices requestsServices = new RequestsServices();

        requestsServices.setUserReq( requestServiceDtoCreate.getUserReq() );
        requestsServices.setAdminRes( requestServiceDtoCreate.getAdminRes() );
        requestsServices.setSvc( requestServiceDtoCreate.getSvc() );
        requestsServices.setStatusReq( requestServiceDtoCreate.getStatusReq() );
        requestsServices.setDateReq( requestServiceDtoCreate.getDateReq() );
        requestsServices.setDateRes( requestServiceDtoCreate.getDateRes() );
        requestsServices.setReasonRes( requestServiceDtoCreate.getReasonRes() );

        return requestsServices;
    }
}

package ecodeli.modelMapper;

import ecodeli.DTO.create.RequestsServicesDTOCreate;
import ecodeli.DTO.read.RequestsServicesDTORead;
import ecodeli.modele.RequestsServices;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-08T15:25:52+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class RequestsServicesMapperImpl implements RequestsServicesMapper {

    @Override
    public RequestsServicesDTORead toDtoRead(RequestsServices requestService) {
        if ( requestService == null ) {
            return null;
        }

        RequestsServicesDTORead requestsServicesDTORead = new RequestsServicesDTORead();

        requestsServicesDTORead.setAdminRes( requestService.getAdminRes() );
        requestsServicesDTORead.setDateReq( requestService.getDateReq() );
        requestsServicesDTORead.setDateRes( requestService.getDateRes() );
        requestsServicesDTORead.setIdReqSvc( requestService.getIdReqSvc() );
        requestsServicesDTORead.setReasonRes( requestService.getReasonRes() );
        requestsServicesDTORead.setStatusReq( requestService.getStatusReq() );
        requestsServicesDTORead.setSvc( requestService.getSvc() );
        requestsServicesDTORead.setUserReq( requestService.getUserReq() );

        return requestsServicesDTORead;
    }

    @Override
    public RequestsServices fromDtoCreate(RequestsServicesDTOCreate requestServiceDtoCreate) {
        if ( requestServiceDtoCreate == null ) {
            return null;
        }

        RequestsServices requestsServices = new RequestsServices();

        requestsServices.setDateReq( requestServiceDtoCreate.getDateReq() );
        requestsServices.setDateRes( requestServiceDtoCreate.getDateRes() );
        requestsServices.setReasonRes( requestServiceDtoCreate.getReasonRes() );
        requestsServices.setStatusReq( requestServiceDtoCreate.getStatusReq() );

        return requestsServices;
    }

    @Override
    public RequestsServices updateRequestServiceFromDtoCreate(RequestsServicesDTOCreate requestServiceDtoCreate, RequestsServices requestService) {
        if ( requestServiceDtoCreate == null ) {
            return requestService;
        }

        if ( requestServiceDtoCreate.getDateReq() != null ) {
            requestService.setDateReq( requestServiceDtoCreate.getDateReq() );
        }
        if ( requestServiceDtoCreate.getDateRes() != null ) {
            requestService.setDateRes( requestServiceDtoCreate.getDateRes() );
        }
        if ( requestServiceDtoCreate.getReasonRes() != null ) {
            requestService.setReasonRes( requestServiceDtoCreate.getReasonRes() );
        }
        if ( requestServiceDtoCreate.getStatusReq() != null ) {
            requestService.setStatusReq( requestServiceDtoCreate.getStatusReq() );
        }

        return requestService;
    }
}

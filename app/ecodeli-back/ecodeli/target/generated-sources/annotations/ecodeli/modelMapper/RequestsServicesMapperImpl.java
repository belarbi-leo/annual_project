package ecodeli.modelMapper;

import ecodeli.DTO.create.RequestsServicesDTOCreate;
import ecodeli.DTO.read.RequestsServicesDTORead;
import ecodeli.modele.RequestsServices;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-07T13:42:24+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.2 (Homebrew)"
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

        requestsServices.setStatusReq( requestServiceDtoCreate.getStatusReq() );
        requestsServices.setDateReq( requestServiceDtoCreate.getDateReq() );
        requestsServices.setDateRes( requestServiceDtoCreate.getDateRes() );
        requestsServices.setReasonRes( requestServiceDtoCreate.getReasonRes() );

        return requestsServices;
    }

    @Override
    public RequestsServices updateRequestServiceFromDtoCreate(RequestsServicesDTOCreate requestServiceDtoCreate, RequestsServices requestService) {
        if ( requestServiceDtoCreate == null ) {
            return requestService;
        }

        if ( requestServiceDtoCreate.getStatusReq() != null ) {
            requestService.setStatusReq( requestServiceDtoCreate.getStatusReq() );
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

        return requestService;
    }
}

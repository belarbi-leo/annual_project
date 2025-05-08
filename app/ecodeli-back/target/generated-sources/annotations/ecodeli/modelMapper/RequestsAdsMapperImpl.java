package ecodeli.modelMapper;

import ecodeli.DTO.create.RequestsAdsDTOCreate;
import ecodeli.DTO.read.RequestsAdsDTORead;
import ecodeli.modele.RequestsAds;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-08T15:25:52+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class RequestsAdsMapperImpl implements RequestsAdsMapper {

    @Override
    public RequestsAdsDTORead toDtoRead(RequestsAds requestAd) {
        if ( requestAd == null ) {
            return null;
        }

        RequestsAdsDTORead requestsAdsDTORead = new RequestsAdsDTORead();

        requestsAdsDTORead.setAd( requestAd.getAd() );
        requestsAdsDTORead.setCountryEndReqAd( requestAd.getCountryEndReqAd() );
        requestsAdsDTORead.setCountryStartReqAd( requestAd.getCountryStartReqAd() );
        requestsAdsDTORead.setDateAcceptReqAd( requestAd.getDateAcceptReqAd() );
        requestsAdsDTORead.setDateCreationReqAd( requestAd.getDateCreationReqAd() );
        requestsAdsDTORead.setDateEndReqAd( requestAd.getDateEndReqAd() );
        requestsAdsDTORead.setDateStartReqAd( requestAd.getDateStartReqAd() );
        requestsAdsDTORead.setIdReqAd( requestAd.getIdReqAd() );
        requestsAdsDTORead.setLatitudeEndReqAd( requestAd.getLatitudeEndReqAd() );
        requestsAdsDTORead.setLatitudeStartReqAd( requestAd.getLatitudeStartReqAd() );
        requestsAdsDTORead.setLocalityEndReqAd( requestAd.getLocalityEndReqAd() );
        requestsAdsDTORead.setLocalityStartReqAd( requestAd.getLocalityStartReqAd() );
        requestsAdsDTORead.setLocationEndReqAd( requestAd.getLocationEndReqAd() );
        requestsAdsDTORead.setLocationStartReqAd( requestAd.getLocationStartReqAd() );
        requestsAdsDTORead.setLongitudeEndReqAd( requestAd.getLongitudeEndReqAd() );
        requestsAdsDTORead.setLongitudeStartReqAd( requestAd.getLongitudeStartReqAd() );
        requestsAdsDTORead.setMessageReqAd( requestAd.getMessageReqAd() );
        requestsAdsDTORead.setPostalCodeEndReqAd( requestAd.getPostalCodeEndReqAd() );
        requestsAdsDTORead.setPostalCodeStartReqAd( requestAd.getPostalCodeStartReqAd() );
        requestsAdsDTORead.setPriceReqAd( requestAd.getPriceReqAd() );
        requestsAdsDTORead.setStateEndReqAd( requestAd.getStateEndReqAd() );
        requestsAdsDTORead.setStateStartReqAd( requestAd.getStateStartReqAd() );
        requestsAdsDTORead.setStatusReqAd( requestAd.getStatusReqAd() );
        requestsAdsDTORead.setSuiteEndReqAd( requestAd.getSuiteEndReqAd() );
        requestsAdsDTORead.setSuiteStartReqAd( requestAd.getSuiteStartReqAd() );
        requestsAdsDTORead.setUser( requestAd.getUser() );

        return requestsAdsDTORead;
    }

    @Override
    public RequestsAds fromDtoCreate(RequestsAdsDTOCreate requestAdDtoCreate) {
        if ( requestAdDtoCreate == null ) {
            return null;
        }

        RequestsAds requestsAds = new RequestsAds();

        requestsAds.setCountryEndReqAd( requestAdDtoCreate.getCountryEndReqAd() );
        requestsAds.setCountryStartReqAd( requestAdDtoCreate.getCountryStartReqAd() );
        requestsAds.setDateAcceptReqAd( requestAdDtoCreate.getDateAcceptReqAd() );
        requestsAds.setDateCreationReqAd( requestAdDtoCreate.getDateCreationReqAd() );
        requestsAds.setDateEndReqAd( requestAdDtoCreate.getDateEndReqAd() );
        requestsAds.setDateStartReqAd( requestAdDtoCreate.getDateStartReqAd() );
        requestsAds.setLatitudeEndReqAd( requestAdDtoCreate.getLatitudeEndReqAd() );
        requestsAds.setLatitudeStartReqAd( requestAdDtoCreate.getLatitudeStartReqAd() );
        requestsAds.setLocalityEndReqAd( requestAdDtoCreate.getLocalityEndReqAd() );
        requestsAds.setLocalityStartReqAd( requestAdDtoCreate.getLocalityStartReqAd() );
        requestsAds.setLocationEndReqAd( requestAdDtoCreate.getLocationEndReqAd() );
        requestsAds.setLocationStartReqAd( requestAdDtoCreate.getLocationStartReqAd() );
        requestsAds.setLongitudeEndReqAd( requestAdDtoCreate.getLongitudeEndReqAd() );
        requestsAds.setLongitudeStartReqAd( requestAdDtoCreate.getLongitudeStartReqAd() );
        requestsAds.setMessageReqAd( requestAdDtoCreate.getMessageReqAd() );
        requestsAds.setPostalCodeEndReqAd( requestAdDtoCreate.getPostalCodeEndReqAd() );
        requestsAds.setPostalCodeStartReqAd( requestAdDtoCreate.getPostalCodeStartReqAd() );
        requestsAds.setPriceReqAd( requestAdDtoCreate.getPriceReqAd() );
        requestsAds.setStateEndReqAd( requestAdDtoCreate.getStateEndReqAd() );
        requestsAds.setStateStartReqAd( requestAdDtoCreate.getStateStartReqAd() );
        requestsAds.setStatusReqAd( requestAdDtoCreate.getStatusReqAd() );
        requestsAds.setSuiteEndReqAd( requestAdDtoCreate.getSuiteEndReqAd() );
        requestsAds.setSuiteStartReqAd( requestAdDtoCreate.getSuiteStartReqAd() );

        return requestsAds;
    }

    @Override
    public RequestsAds updateRequestAdFromDtoCreate(RequestsAdsDTOCreate requestAdDtoCreate, RequestsAds requestAd) {
        if ( requestAdDtoCreate == null ) {
            return requestAd;
        }

        if ( requestAdDtoCreate.getCountryEndReqAd() != null ) {
            requestAd.setCountryEndReqAd( requestAdDtoCreate.getCountryEndReqAd() );
        }
        if ( requestAdDtoCreate.getCountryStartReqAd() != null ) {
            requestAd.setCountryStartReqAd( requestAdDtoCreate.getCountryStartReqAd() );
        }
        if ( requestAdDtoCreate.getDateAcceptReqAd() != null ) {
            requestAd.setDateAcceptReqAd( requestAdDtoCreate.getDateAcceptReqAd() );
        }
        if ( requestAdDtoCreate.getDateCreationReqAd() != null ) {
            requestAd.setDateCreationReqAd( requestAdDtoCreate.getDateCreationReqAd() );
        }
        if ( requestAdDtoCreate.getDateEndReqAd() != null ) {
            requestAd.setDateEndReqAd( requestAdDtoCreate.getDateEndReqAd() );
        }
        if ( requestAdDtoCreate.getDateStartReqAd() != null ) {
            requestAd.setDateStartReqAd( requestAdDtoCreate.getDateStartReqAd() );
        }
        if ( requestAdDtoCreate.getLatitudeEndReqAd() != null ) {
            requestAd.setLatitudeEndReqAd( requestAdDtoCreate.getLatitudeEndReqAd() );
        }
        if ( requestAdDtoCreate.getLatitudeStartReqAd() != null ) {
            requestAd.setLatitudeStartReqAd( requestAdDtoCreate.getLatitudeStartReqAd() );
        }
        if ( requestAdDtoCreate.getLocalityEndReqAd() != null ) {
            requestAd.setLocalityEndReqAd( requestAdDtoCreate.getLocalityEndReqAd() );
        }
        if ( requestAdDtoCreate.getLocalityStartReqAd() != null ) {
            requestAd.setLocalityStartReqAd( requestAdDtoCreate.getLocalityStartReqAd() );
        }
        if ( requestAdDtoCreate.getLocationEndReqAd() != null ) {
            requestAd.setLocationEndReqAd( requestAdDtoCreate.getLocationEndReqAd() );
        }
        if ( requestAdDtoCreate.getLocationStartReqAd() != null ) {
            requestAd.setLocationStartReqAd( requestAdDtoCreate.getLocationStartReqAd() );
        }
        if ( requestAdDtoCreate.getLongitudeEndReqAd() != null ) {
            requestAd.setLongitudeEndReqAd( requestAdDtoCreate.getLongitudeEndReqAd() );
        }
        if ( requestAdDtoCreate.getLongitudeStartReqAd() != null ) {
            requestAd.setLongitudeStartReqAd( requestAdDtoCreate.getLongitudeStartReqAd() );
        }
        if ( requestAdDtoCreate.getMessageReqAd() != null ) {
            requestAd.setMessageReqAd( requestAdDtoCreate.getMessageReqAd() );
        }
        if ( requestAdDtoCreate.getPostalCodeEndReqAd() != null ) {
            requestAd.setPostalCodeEndReqAd( requestAdDtoCreate.getPostalCodeEndReqAd() );
        }
        if ( requestAdDtoCreate.getPostalCodeStartReqAd() != null ) {
            requestAd.setPostalCodeStartReqAd( requestAdDtoCreate.getPostalCodeStartReqAd() );
        }
        if ( requestAdDtoCreate.getPriceReqAd() != null ) {
            requestAd.setPriceReqAd( requestAdDtoCreate.getPriceReqAd() );
        }
        if ( requestAdDtoCreate.getStateEndReqAd() != null ) {
            requestAd.setStateEndReqAd( requestAdDtoCreate.getStateEndReqAd() );
        }
        if ( requestAdDtoCreate.getStateStartReqAd() != null ) {
            requestAd.setStateStartReqAd( requestAdDtoCreate.getStateStartReqAd() );
        }
        if ( requestAdDtoCreate.getStatusReqAd() != null ) {
            requestAd.setStatusReqAd( requestAdDtoCreate.getStatusReqAd() );
        }
        if ( requestAdDtoCreate.getSuiteEndReqAd() != null ) {
            requestAd.setSuiteEndReqAd( requestAdDtoCreate.getSuiteEndReqAd() );
        }
        if ( requestAdDtoCreate.getSuiteStartReqAd() != null ) {
            requestAd.setSuiteStartReqAd( requestAdDtoCreate.getSuiteStartReqAd() );
        }

        return requestAd;
    }
}

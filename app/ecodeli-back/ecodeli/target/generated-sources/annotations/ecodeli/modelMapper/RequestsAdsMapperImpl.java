package ecodeli.modelMapper;

import ecodeli.DTO.create.RequestsAdsDTOCreate;
import ecodeli.DTO.read.RequestsAdsDTORead;
import ecodeli.modele.RequestsAds;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-04T16:32:48+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.2 (Homebrew)"
)
@Component
public class RequestsAdsMapperImpl implements RequestsAdsMapper {

    @Override
    public RequestsAdsDTORead toDtoRead(RequestsAds requestAd) {
        if ( requestAd == null ) {
            return null;
        }

        RequestsAdsDTORead requestsAdsDTORead = new RequestsAdsDTORead();

        requestsAdsDTORead.setIdReqAd( requestAd.getIdReqAd() );
        requestsAdsDTORead.setUser( requestAd.getUser() );
        requestsAdsDTORead.setAd( requestAd.getAd() );
        requestsAdsDTORead.setStatusReqAd( requestAd.getStatusReqAd() );
        requestsAdsDTORead.setDateCreationReqAd( requestAd.getDateCreationReqAd() );
        requestsAdsDTORead.setDateAcceptReqAd( requestAd.getDateAcceptReqAd() );
        requestsAdsDTORead.setDateStartReqAd( requestAd.getDateStartReqAd() );
        requestsAdsDTORead.setLocationStartReqAd( requestAd.getLocationStartReqAd() );
        requestsAdsDTORead.setSuiteStartReqAd( requestAd.getSuiteStartReqAd() );
        requestsAdsDTORead.setLocalityStartReqAd( requestAd.getLocalityStartReqAd() );
        requestsAdsDTORead.setStateStartReqAd( requestAd.getStateStartReqAd() );
        requestsAdsDTORead.setPostalCodeStartReqAd( requestAd.getPostalCodeStartReqAd() );
        requestsAdsDTORead.setCountryStartReqAd( requestAd.getCountryStartReqAd() );
        requestsAdsDTORead.setLatitudeStartReqAd( requestAd.getLatitudeStartReqAd() );
        requestsAdsDTORead.setLongitudeStartReqAd( requestAd.getLongitudeStartReqAd() );
        requestsAdsDTORead.setDateEndReqAd( requestAd.getDateEndReqAd() );
        requestsAdsDTORead.setLocationEndReqAd( requestAd.getLocationEndReqAd() );
        requestsAdsDTORead.setSuiteEndReqAd( requestAd.getSuiteEndReqAd() );
        requestsAdsDTORead.setLocalityEndReqAd( requestAd.getLocalityEndReqAd() );
        requestsAdsDTORead.setStateEndReqAd( requestAd.getStateEndReqAd() );
        requestsAdsDTORead.setPostalCodeEndReqAd( requestAd.getPostalCodeEndReqAd() );
        requestsAdsDTORead.setCountryEndReqAd( requestAd.getCountryEndReqAd() );
        requestsAdsDTORead.setLatitudeEndReqAd( requestAd.getLatitudeEndReqAd() );
        requestsAdsDTORead.setLongitudeEndReqAd( requestAd.getLongitudeEndReqAd() );
        requestsAdsDTORead.setMessageReqAd( requestAd.getMessageReqAd() );
        requestsAdsDTORead.setPriceReqAd( requestAd.getPriceReqAd() );

        return requestsAdsDTORead;
    }

    @Override
    public RequestsAds fromDtoCreate(RequestsAdsDTOCreate requestAdDtoCreate) {
        if ( requestAdDtoCreate == null ) {
            return null;
        }

        RequestsAds requestsAds = new RequestsAds();

        requestsAds.setStatusReqAd( requestAdDtoCreate.getStatusReqAd() );
        requestsAds.setDateCreationReqAd( requestAdDtoCreate.getDateCreationReqAd() );
        requestsAds.setDateAcceptReqAd( requestAdDtoCreate.getDateAcceptReqAd() );
        requestsAds.setDateStartReqAd( requestAdDtoCreate.getDateStartReqAd() );
        requestsAds.setLocationStartReqAd( requestAdDtoCreate.getLocationStartReqAd() );
        requestsAds.setSuiteStartReqAd( requestAdDtoCreate.getSuiteStartReqAd() );
        requestsAds.setLocalityStartReqAd( requestAdDtoCreate.getLocalityStartReqAd() );
        requestsAds.setStateStartReqAd( requestAdDtoCreate.getStateStartReqAd() );
        requestsAds.setPostalCodeStartReqAd( requestAdDtoCreate.getPostalCodeStartReqAd() );
        requestsAds.setCountryStartReqAd( requestAdDtoCreate.getCountryStartReqAd() );
        requestsAds.setLatitudeStartReqAd( requestAdDtoCreate.getLatitudeStartReqAd() );
        requestsAds.setLongitudeStartReqAd( requestAdDtoCreate.getLongitudeStartReqAd() );
        requestsAds.setDateEndReqAd( requestAdDtoCreate.getDateEndReqAd() );
        requestsAds.setLocationEndReqAd( requestAdDtoCreate.getLocationEndReqAd() );
        requestsAds.setSuiteEndReqAd( requestAdDtoCreate.getSuiteEndReqAd() );
        requestsAds.setLocalityEndReqAd( requestAdDtoCreate.getLocalityEndReqAd() );
        requestsAds.setStateEndReqAd( requestAdDtoCreate.getStateEndReqAd() );
        requestsAds.setPostalCodeEndReqAd( requestAdDtoCreate.getPostalCodeEndReqAd() );
        requestsAds.setCountryEndReqAd( requestAdDtoCreate.getCountryEndReqAd() );
        requestsAds.setLatitudeEndReqAd( requestAdDtoCreate.getLatitudeEndReqAd() );
        requestsAds.setLongitudeEndReqAd( requestAdDtoCreate.getLongitudeEndReqAd() );
        requestsAds.setMessageReqAd( requestAdDtoCreate.getMessageReqAd() );
        requestsAds.setPriceReqAd( requestAdDtoCreate.getPriceReqAd() );

        return requestsAds;
    }

    @Override
    public RequestsAds updateRequestAdFromDtoCreate(RequestsAdsDTOCreate requestAdDtoCreate, RequestsAds requestAd) {
        if ( requestAdDtoCreate == null ) {
            return requestAd;
        }

        if ( requestAdDtoCreate.getStatusReqAd() != null ) {
            requestAd.setStatusReqAd( requestAdDtoCreate.getStatusReqAd() );
        }
        if ( requestAdDtoCreate.getDateCreationReqAd() != null ) {
            requestAd.setDateCreationReqAd( requestAdDtoCreate.getDateCreationReqAd() );
        }
        if ( requestAdDtoCreate.getDateAcceptReqAd() != null ) {
            requestAd.setDateAcceptReqAd( requestAdDtoCreate.getDateAcceptReqAd() );
        }
        if ( requestAdDtoCreate.getDateStartReqAd() != null ) {
            requestAd.setDateStartReqAd( requestAdDtoCreate.getDateStartReqAd() );
        }
        if ( requestAdDtoCreate.getLocationStartReqAd() != null ) {
            requestAd.setLocationStartReqAd( requestAdDtoCreate.getLocationStartReqAd() );
        }
        if ( requestAdDtoCreate.getSuiteStartReqAd() != null ) {
            requestAd.setSuiteStartReqAd( requestAdDtoCreate.getSuiteStartReqAd() );
        }
        if ( requestAdDtoCreate.getLocalityStartReqAd() != null ) {
            requestAd.setLocalityStartReqAd( requestAdDtoCreate.getLocalityStartReqAd() );
        }
        if ( requestAdDtoCreate.getStateStartReqAd() != null ) {
            requestAd.setStateStartReqAd( requestAdDtoCreate.getStateStartReqAd() );
        }
        if ( requestAdDtoCreate.getPostalCodeStartReqAd() != null ) {
            requestAd.setPostalCodeStartReqAd( requestAdDtoCreate.getPostalCodeStartReqAd() );
        }
        if ( requestAdDtoCreate.getCountryStartReqAd() != null ) {
            requestAd.setCountryStartReqAd( requestAdDtoCreate.getCountryStartReqAd() );
        }
        if ( requestAdDtoCreate.getLatitudeStartReqAd() != null ) {
            requestAd.setLatitudeStartReqAd( requestAdDtoCreate.getLatitudeStartReqAd() );
        }
        if ( requestAdDtoCreate.getLongitudeStartReqAd() != null ) {
            requestAd.setLongitudeStartReqAd( requestAdDtoCreate.getLongitudeStartReqAd() );
        }
        if ( requestAdDtoCreate.getDateEndReqAd() != null ) {
            requestAd.setDateEndReqAd( requestAdDtoCreate.getDateEndReqAd() );
        }
        if ( requestAdDtoCreate.getLocationEndReqAd() != null ) {
            requestAd.setLocationEndReqAd( requestAdDtoCreate.getLocationEndReqAd() );
        }
        if ( requestAdDtoCreate.getSuiteEndReqAd() != null ) {
            requestAd.setSuiteEndReqAd( requestAdDtoCreate.getSuiteEndReqAd() );
        }
        if ( requestAdDtoCreate.getLocalityEndReqAd() != null ) {
            requestAd.setLocalityEndReqAd( requestAdDtoCreate.getLocalityEndReqAd() );
        }
        if ( requestAdDtoCreate.getStateEndReqAd() != null ) {
            requestAd.setStateEndReqAd( requestAdDtoCreate.getStateEndReqAd() );
        }
        if ( requestAdDtoCreate.getPostalCodeEndReqAd() != null ) {
            requestAd.setPostalCodeEndReqAd( requestAdDtoCreate.getPostalCodeEndReqAd() );
        }
        if ( requestAdDtoCreate.getCountryEndReqAd() != null ) {
            requestAd.setCountryEndReqAd( requestAdDtoCreate.getCountryEndReqAd() );
        }
        if ( requestAdDtoCreate.getLatitudeEndReqAd() != null ) {
            requestAd.setLatitudeEndReqAd( requestAdDtoCreate.getLatitudeEndReqAd() );
        }
        if ( requestAdDtoCreate.getLongitudeEndReqAd() != null ) {
            requestAd.setLongitudeEndReqAd( requestAdDtoCreate.getLongitudeEndReqAd() );
        }
        if ( requestAdDtoCreate.getMessageReqAd() != null ) {
            requestAd.setMessageReqAd( requestAdDtoCreate.getMessageReqAd() );
        }
        if ( requestAdDtoCreate.getPriceReqAd() != null ) {
            requestAd.setPriceReqAd( requestAdDtoCreate.getPriceReqAd() );
        }

        return requestAd;
    }
}

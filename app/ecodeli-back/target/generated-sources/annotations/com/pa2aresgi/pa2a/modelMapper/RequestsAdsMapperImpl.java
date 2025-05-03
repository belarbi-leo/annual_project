package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.RequestsAdsDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.RequestsAdsDTORead;
import com.pa2aresgi.pa2a.modele.RequestsAds;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-02T20:10:08+0200",
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

        requestsAds.setUser( requestAdDtoCreate.getUser() );
        requestsAds.setAd( requestAdDtoCreate.getAd() );
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
}

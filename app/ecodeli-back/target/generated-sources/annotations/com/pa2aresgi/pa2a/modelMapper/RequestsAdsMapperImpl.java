package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.RequestsAdsDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.RequestsAdsDTORead;
import com.pa2aresgi.pa2a.modele.RequestsAds;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-25T10:17:59+0200",
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

        requestsAds.setAd( requestAdDtoCreate.getAd() );
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
        requestsAds.setUser( requestAdDtoCreate.getUser() );

        return requestsAds;
    }
}

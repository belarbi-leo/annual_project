package com.pa2aresgi.pa2a.DTO.create;

import com.pa2aresgi.pa2a.enumeratation.StatusReqAnnonceEnum;
import com.pa2aresgi.pa2a.modele.Ads;
import com.pa2aresgi.pa2a.modele.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class RequestsAdsDTOCreate {
    private Users user;
    private Ads ad;
    private StatusReqAnnonceEnum statusReqAd;
    private Timestamp dateCreationReqAd;
    private Timestamp dateAcceptReqAd;
    private Timestamp dateStartReqAd;
    private String locationStartReqAd;
    private String suiteStartReqAd;
    private String localityStartReqAd;
    private String stateStartReqAd;
    private String postalCodeStartReqAd;
    private String countryStartReqAd;
    private Float latitudeStartReqAd;
    private Float longitudeStartReqAd;
    private Timestamp dateEndReqAd;
    private String locationEndReqAd;
    private String suiteEndReqAd;
    private String localityEndReqAd;
    private String stateEndReqAd;
    private String postalCodeEndReqAd;
    private String countryEndReqAd;
    private Float latitudeEndReqAd;
    private Float longitudeEndReqAd;
    private String messageReqAd;
    private Float priceReqAd;
}

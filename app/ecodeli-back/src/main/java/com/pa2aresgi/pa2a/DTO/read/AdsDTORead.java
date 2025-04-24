package com.pa2aresgi.pa2a.DTO.read;

import com.pa2aresgi.pa2a.enumeratation.StatusAdEnum;
import com.pa2aresgi.pa2a.modele.Services;
import com.pa2aresgi.pa2a.modele.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class AdsDTORead {
    private Integer idAd;
    private Users userCreator;
    private Users userAccept;
    private Services svc;
    private StatusAdEnum statusAd;
    private Timestamp dateCreationAd;
    private Timestamp dateAcceptAd;
    private Timestamp dateStartAd;
    private String streetStartAd;
    private String postalCodeStartAd;
    private String countryStartAd;
    private Float latitudeStartAd;
    private Float longitudeStartAd;
    private Timestamp dateEndAd;
    private String streetEndAd;
    private String postalCodeEndAd;
    private String countryEndAd;
    private Float latitudeEndAd;
    private Float longitudeEndAd;
    private String descriptionAd;
    private Float priceAd;
    private String photoAd;
}

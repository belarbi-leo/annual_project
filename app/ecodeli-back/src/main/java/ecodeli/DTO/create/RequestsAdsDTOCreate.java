package ecodeli.DTO.create;

import ecodeli.enumeratation.StatusReqAnnonceEnum;
import ecodeli.modele.Ads;
import ecodeli.modele.Users;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class RequestsAdsDTOCreate {
    @NotNull(message = "An id user is required")
    private Integer user;
    @NotNull(message = "An id ad is required")
    private Integer ad;
    //DEFAULT 'pending'
    private StatusReqAnnonceEnum statusReqAd;
    //DEFAULT now()
    private Timestamp dateCreationReqAd;
    private Timestamp dateAcceptReqAd;
    private Timestamp dateStartReqAd;
    @Size(max = 255, message = "Start location must be less than 255 characters")
    private String locationStartReqAd;
    @Size(max = 255, message = "Start suite must be less than 255 characters")
    private String suiteStartReqAd;
    @Size(max = 255, message = "Start locality must be less than 255 characters")
    private String localityStartReqAd;
    @Size(max = 255, message = "Start state must be less than 255 characters")
    private String stateStartReqAd;
    @Size(max = 20, message = "Start postal code must be less than 20 characters")
    private String postalCodeStartReqAd;
    @Size(max = 100, message = "Start country must be less than 100 characters")
    private String countryStartReqAd;
    private Float latitudeStartReqAd;
    private Float longitudeStartReqAd;
    private Timestamp dateEndReqAd;
    @Size(max = 255, message = "End location must be less than 255 characters")
    private String locationEndReqAd;
    @Size(max = 255, message = "End suite must be less than 255 characters")
    private String suiteEndReqAd;
    @Size(max = 255, message = "End locality must be less than 255 characters")
    private String localityEndReqAd;
    @Size(max = 255, message = "End state must be less than 255 characters")
    private String stateEndReqAd;
    @Size(max = 20, message = "End postal code must be less than 20 characters")
    private String postalCodeEndReqAd;
    @Size(max = 100, message = "End country must be less than 100 characters")
    private String countryEndReqAd;
    private Float latitudeEndReqAd;
    private Float longitudeEndReqAd;
    @Size(max = 255, message = "Message must be less than 255 characters")
    private String messageReqAd;
    //DEFAULT 0.0
    private Float priceReqAd;
}

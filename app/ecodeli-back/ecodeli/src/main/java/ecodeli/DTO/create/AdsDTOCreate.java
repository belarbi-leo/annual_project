package ecodeli.DTO.create;

import ecodeli.enumeratation.StatusAdEnum;
import ecodeli.modele.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
@NoArgsConstructor
public class AdsDTOCreate {
    @NotNull(message = "User creator must be informed")
    private Integer userCreator;
    @NotNull(message = "User accepting must be informed")
    private Integer userAccept;
    @NotNull(message = "Service must be informed")
    private Integer svc;
    //DEFAULT 'pending'
    private StatusAdEnum statusAd;
    //DEFAULT now()
    private Timestamp dateCreationAd;
    private Timestamp dateAcceptAd;
    private Timestamp dateStartAd;
    //private String streetStartAd;
    @Size(max = 255, message = "Start location must be less than 255 characters")
    private String locationStart;
    @Size(max = 255, message = "Start additional address must be less than 255 characters")
    private String suiteStart;
    @Size(max = 255, message = "Start locality must be less than 255 characters")
    private String localityStart;
    @Size(max = 255, message = "Start state must be less than 255 characters")
    private String stateStart;
    @Size(max = 20, message = "Start postal code must be less than 20 characters")
    private String postalCodeStartAd;
    @Size(max = 100, message = "Start country must be less than 100 characters")
    private String countryStartAd;
    private Float latitudeStartAd;
    private Float longitudeStartAd;
    private Timestamp dateEndAd;
    //private String streetEndAd;
    @Size(max = 255, message = "End location must be less than 255 characters")
    private String locationEnd;
    @Size(max = 255, message = "End suite must be less than 255 characters")
    private String suiteEnd;
    @Size(max = 255, message = "End locality must be less than 255 characters")
    private String localityEnd;
    @Size(max = 255, message = "End state must be less than 255 characters")
    private String stateEnd;
    @Size(max = 20, message = "End postal code must be less than 20 characters")
    private String postalCodeEndAd;
    @Size(max = 100, message = "End country must be less than 100 characters")
    private String countryEndAd;
    private Float latitudeEndAd;
    private Float longitudeEndAd;
    @Size(max = 255, message = "Description must be less than 255 characters")
    private String descriptionAd;
    @NotNull(message = "The price for the ad must be informed")
    private Float priceAd;
    private String photoAd;
    @NotBlank(message = "Ad's title can't be blank")
    @Size(max = 255, message = "Ad's title must be less than 255 characters")
    private String titleAd;
}

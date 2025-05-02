package ecodeli.DTO.read;

import ecodeli.enumeratation.StatusAdEnum;
import ecodeli.modele.Services;
import ecodeli.modele.Users;
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
    //private String streetStartAd;
    private String locationStart;
    private String suiteStart;
    private String localityStart;
    private String stateStart;
    private String postalCodeStartAd;
    private String countryStartAd;
    private Float latitudeStartAd;
    private Float longitudeStartAd;
    private Timestamp dateEndAd;
    //private String streetEndAd;
    private String locationEnd;
    private String suiteEnd;
    private String localityEnd;
    private String stateEnd;
    private String postalCodeEndAd;
    private String countryEndAd;
    private Float latitudeEndAd;
    private Float longitudeEndAd;
    private String descriptionAd;
    private Float priceAd;
    private String photoAd;
    private String titleAd;
}

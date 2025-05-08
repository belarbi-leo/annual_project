package ecodeli.modele;

import ecodeli.enumeratation.StatusReqAnnonceEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name="requests_ads")
@Getter
@Setter
@NoArgsConstructor
public class RequestsAds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_req_ad")
    private Integer idReqAd;
    @ManyToOne
    @JoinColumn(name="id_user", nullable = false)
    private Users user;
    @ManyToOne
    @JoinColumn(name="id_ad", nullable = false)
    private Ads ad;
    @Column(name="status_req_ad")
    @Enumerated(EnumType.STRING)
    private StatusReqAnnonceEnum statusReqAd;
    @Column(name="date_creation_req_ad")
    private Timestamp dateCreationReqAd;
    @Column(name="date_accept_req_ad")
    private Timestamp dateAcceptReqAd;
    @Column(name="date_start_req_ad")
    private Timestamp dateStartReqAd;
    /*@Column(length=255)
    private String street_start_req_annonce;*/
    @Column(name="location_start_req_ad", length=255)
    private String locationStartReqAd;
    @Column(name="suite_start_req_ad", length = 255)
    private String suiteStartReqAd;
    @Column(name="locality_start_req_ad", length=255)
    private String localityStartReqAd;
    @Column(name="state_start_req_ad", length = 255)
    private String stateStartReqAd;
    @Column(name="postal_code_start_req_ad", length=20)
    private String postalCodeStartReqAd;
    @Column(name="country_start_req_ad", length=100)
    private String countryStartReqAd;
    @Column(name="latitude_start_req_ad", columnDefinition = "decimal(9,6)")
    private Float latitudeStartReqAd;
    @Column(name="longitude_start_req_ad", columnDefinition = "decimal(9,6)")
    private Float longitudeStartReqAd;
    @Column(name="date_end_req_ad")
    private Timestamp dateEndReqAd;
    /*@Column(length=255)
    private String street_end_req_ad;*/
    @Column(name="location_end_req_ad", length=255)
    private String locationEndReqAd;
    @Column(name="suite_end_req_ad", length = 255)
    private String suiteEndReqAd;
    @Column(name="locality_end_req_ad", length=255)
    private String localityEndReqAd;
    @Column(name="state_end_req_ad", length = 255)
    private String stateEndReqAd;
    @Column(name="postal_code_end_req_ad", length=20)
    private String postalCodeEndReqAd;
    @Column(name="country_end_req_ad", length=100)
    private String countryEndReqAd;
    @Column(name="latitude_end_req_ad", columnDefinition = "decimal(9,6)")
    private Float latitudeEndReqAd;
    @Column(name="longitude_end_req_ad", columnDefinition = "decimal(9,6)")
    private Float longitudeEndReqAd;
    @Column(name="message_req_ad", length=255)
    private String messageReqAd;
    @Column(name="price_req_ad", columnDefinition="decimal(10,2)")
    private Float priceReqAd;
}

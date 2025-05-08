package ecodeli.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ecodeli.enumeratation.StatusAdEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="ads")
@Getter
@Setter
@NoArgsConstructor
public class Ads {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_ad")
    private Integer idAd;
    @ManyToOne
    @JoinColumn(name="id_user_creator", nullable = false)
    private Users userCreator;
    @ManyToOne
    @JoinColumn(name="id_user_accept")
    private Users userAccept;
    @ManyToOne
    @JoinColumn(name="id_svc", nullable = false)
    private Services svc;
    /*@ManyToOne
    @JoinColumn(name="id_sub", nullable = false)
    private Subscriptions id_sub;*/
    @Column(name="status_ad")
    @Enumerated(EnumType.STRING)
    private StatusAdEnum statusAd;
    @Column(name="date_creation_ad")
    private Timestamp dateCreationAd;
    @Column(name="date_accept_ad")
    private Timestamp dateAcceptAd;
    @Column(name="date_start_ad")
    private Timestamp dateStartAd;
    /*@Column(length=255)
    private String street_start_ad;*/
    @Column(name="location_start", length=255)
    private String locationStart;
    @Column(name="suite_start", length = 255)
    private String suiteStart;
    @Column(name="locality_start", length=255)
    private String localityStart;
    @Column(name="state_start", length = 255)
    private String stateStart;
    @Column(name="postal_code_start", length=20)
    private String postalCodeStart;
    @Column(name="country_start", length=100)
    private String countryStart;
    @Column(name="latitude_start", columnDefinition = "decimal(9,6)")
    private Float latitudeStart;
    @Column(name="longitude_start", columnDefinition = "decimal(9,6)")
    private Float longitudeStart;
    @Column(name="date_end_ad")
    private Timestamp dateEndAd;
    /*@Column(length = 255)
    private String street_end_ad;*/
    @Column(name="location_end", length=255)
    private String locationEnd;
    @Column(name="suite_end", length = 255)
    private String suiteEnd;
    @Column(name="locality_end", length=255)
    private String localityEnd;
    @Column(name="state_end", length = 255)
    private String stateEnd;
    @Column(name="postal_code_end", length=20)
    private String postalCodeEnd;
    @Column(name="country_end", length=100)
    private String countryEnd;
    @Column(name="latitude_end", columnDefinition = "decimal(9,6)")
    private Float latitudeEnd;
    @Column(name="longitude_end", columnDefinition = "decimal(9,6)")
    private Float longitudeEnd;
    @Column(name="descrpition_ad", length = 255)
    private String descriptionAd;
    @Column(name="price_ad", columnDefinition="decimal(10,2)")
    private Float priceAd;
    @Column(name="photo_ad", columnDefinition="text")
    private String photoAd;
    @Column(name="title_ad", length = 255)
    private String titleAd;
    @OneToMany(mappedBy = "ad")
    @JsonIgnore
    private List<RequestsAds> requestsAdsList = new ArrayList<>();
    @OneToMany(mappedBy = "ad")
    @JsonIgnore
    private List<Packages> packagesList = new ArrayList<>();
    @OneToMany(mappedBy = "ad")
    @JsonIgnore
    private List<Opinions> opinionsList = new ArrayList<>();
    @OneToMany(mappedBy = "ad")
    @JsonIgnore
    private List<Payments> paymentsList = new ArrayList<>();
    @OneToMany(mappedBy = "ad")
    @JsonIgnore
    private List<Disputes> disputesList = new ArrayList<>();
    @ManyToMany(mappedBy="adsSet")
    @JsonIgnore
    private Set<Routes> routesSet = new HashSet<>();
}

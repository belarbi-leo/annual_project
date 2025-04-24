package com.pa2aresgi.pa2a.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="routes")
@Getter
@Setter
@NoArgsConstructor
public class Routes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_route")
    private Integer idRoute;
    @ManyToOne
    @JoinColumn(name="id_user", nullable = false)
    private Users user;
    @Column(name="date_creation_route")
    private Timestamp dateCreationRoute;
    @Column(name="date_start_route")
    private Timestamp dateStartRoute;
    /*@Column(length=255)
    private String street_start_route;*/
    @Column(name="location_start_route", length=255)
    private String locationStartRoute;
    @Column(name="suite_start_route", length = 255)
    private String suiteStartRoute;
    @Column(name="locality_start_route", length=255)
    private String localityStartRoute;
    @Column(name="state_start_route", length = 255)
    private String stateStartRoute;
    @Column(name="postal_code_start_route", length=20)
    private String postalCodeStartRoute;
    @Column(name="country_start_route", length=100)
    private String countryStartRoute;
    @Column(name="latitude_start_route", columnDefinition = "decimal(9,6)")
    private Float latitudeStartRoute;
    @Column(name="longitude_start_route", columnDefinition = "decimal(9,6)")
    private Float longitudeStartRoute;
    @Column(name="date_end_route")
    private Timestamp dateEndRoute;
    /*@Column(length=255)
    private String street_end_route;*/
    @Column(name="location_end_route", length=255)
    private String locationEndRoute;
    @Column(name="suite_end_route", length = 255)
    private String suiteEndRoute;
    @Column(name="locality_end_route", length=255)
    private String localityEndRoute;
    @Column(name="state_end_route", length = 255)
    private String stateEndRoute;
    @Column(name="postal_code_end_route", length=20)
    private String postalCodeEndRoute;
    @Column(name="country_end_route", length=100)
    private String countryEndRoute;
    @Column(name="latitude_end_route", columnDefinition = "decimal(9,6)")
    private Float latitudeEndRoute;
    @Column(name="longitude_end_route", columnDefinition = "decimal(9,6)")
    private Float longitudeEndRoute;
    @Column(name="description_route", columnDefinition="text")
    private String descriptionRoute;
    @Column(name="step_route")
    private Integer stepRoute;
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "matches",
            joinColumns = @JoinColumn(name="id_route"),
            inverseJoinColumns = @JoinColumn(name="id_ad"))
    @JsonIgnore
    private Set<Ads> adsSet = new HashSet<>();
}

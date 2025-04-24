package com.pa2aresgi.pa2a.DTO.read;

import com.pa2aresgi.pa2a.modele.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class RoutesDTORead {
    private Integer idRoute;
    private Users user;
    private Timestamp dateCreationRoute;
    private Timestamp dateStartRoute;
    private String locationStartRoute;
    private String suiteStartRoute;
    private String localityStartRoute;
    private String stateStartRoute;
    private String postalCodeStartRoute;
    private String countryStartRoute;
    private Float latitudeStartRoute;
    private Float longitudeStartRoute;
    private Timestamp dateEndRoute;
    private String locationEndRoute;
    private String suiteEndRoute;
    private String localityEndRoute;
    private String stateEndRoute;
    private String postalCodeEndRoute;
    private String countryEndRoute;
    private Float latitudeEndRoute;
    private Float longitudeEndRoute;
    private String descriptionRoute;
    private Integer stepRoute;
}

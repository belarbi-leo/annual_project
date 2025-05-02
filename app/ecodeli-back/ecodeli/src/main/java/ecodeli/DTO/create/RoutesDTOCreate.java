package ecodeli.DTO.create;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class RoutesDTOCreate {
    @NotNull(message = "The user must be informed")
    private Integer user;
    //DEFAULT now()
    private Timestamp dateCreationRoute;
    private Timestamp dateStartRoute;
    @Size(max = 255, message = "Start location must be less than 255 characters")
    private String locationStartRoute;
    @Size(max = 255, message = "Start additional address must be less than 255 characters")
    private String suiteStartRoute;
    @Size(max = 255, message = "Start locality's name must be less than 255 characters")
    private String localityStartRoute;
    @Size(max = 255, message = "Start state must be less than 255 characters")
    private String stateStartRoute;
    @Size(max = 20, message = "Start postal code must be less than 20 characters")
    private String postalCodeStartRoute;
    @Size(max = 100, message = "Start country's name must be less than 100 characters")
    private String countryStartRoute;
    private Float latitudeStartRoute;
    private Float longitudeStartRoute;
    private Timestamp dateEndRoute;
    @Size(max = 255, message = "End location must be less than 255 characters")
    private String locationEndRoute;
    @Size(max = 255, message = "End additional address must be less than 255 characters")
    private String suiteEndRoute;
    @Size(max = 255, message = "End locality's name must be less than 255 characters")
    private String localityEndRoute;
    @Size(max = 255, message = "End state must be less than 255 characters")
    private String stateEndRoute;
    @Size(max = 20, message = "End postal code must be less than 20 characters")
    private String postalCodeEndRoute;
    @Size(max = 100, message = "End country's name must be less than 100 characters")
    private String countryEndRoute;
    private Float latitudeEndRoute;
    private Float longitudeEndRoute;
    private String descriptionRoute;
    //DEFAULT 0
    private Integer stepRoute;
}

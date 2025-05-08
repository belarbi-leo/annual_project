package ecodeli.DTO.create;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DepotsDTOCreate {
    private Integer storageCapacityDepot;
    @Size(max = 255, message = "Location must be less than 255 characters")
    private String location;
    @Size(max = 255, message = "Additional address must be less than 255 characters")
    private String suite;
    @Size(max = 255, message = "Locality must be less than 255 characters")
    private String locality;
    @Size(max = 255, message = "State must be less than 255 characters")
    private String state;
    @Size(max = 20, message = "Postal code must be less than 20 characters")
    private String postalCode;
    @Size(max = 100, message = " must be less than 100 characters")
    private String country;
    private Float latitude;
    private Float longitude;
}

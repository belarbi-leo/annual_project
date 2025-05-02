package ecodeli.DTO.read;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DepotsDTORead {
    private Integer idDepot;
    private Integer storageCapacityDepot;
    private String location;
    private String suite;
    private String locality;
    private String state;
    private String postalCode;
    private String country;
    private Float latitude;
    private Float longitude;
}

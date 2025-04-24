package com.pa2aresgi.pa2a.DTO.create;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DepotsDTOCreate {
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

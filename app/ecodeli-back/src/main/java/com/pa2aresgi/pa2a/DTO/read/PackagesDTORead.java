package com.pa2aresgi.pa2a.DTO.read;

import com.pa2aresgi.pa2a.modele.Ads;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PackagesDTORead {
    private Integer idPack;
    private Ads ad;
    private String contentPack;
    private Integer quantityPack;
    private String detailsPack;
    private Float weightPack;
    private Integer lengthPack;
    private Integer widthPack;
    private Integer heightPack;
    private String photoPack;
    private Boolean fragile;
}

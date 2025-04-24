package com.pa2aresgi.pa2a.DTO.create;

import com.pa2aresgi.pa2a.modele.Ads;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class OpinionsDTOCreate {
    private Ads ad;
    private short noteOpinion;
    private String titleOpinion;
    private String descriptionOpinion;
    private Timestamp dateOpinion;
}

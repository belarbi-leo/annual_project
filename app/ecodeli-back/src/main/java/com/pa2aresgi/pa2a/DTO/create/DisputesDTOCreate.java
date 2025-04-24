package com.pa2aresgi.pa2a.DTO.create;

import com.pa2aresgi.pa2a.enumeratation.StatusDisputeEnum;
import com.pa2aresgi.pa2a.modele.Ads;
import com.pa2aresgi.pa2a.modele.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class DisputesDTOCreate {
    private Ads ad;
    private Users user;
    private Timestamp dateStatusDispute;
    private StatusDisputeEnum statusDispute;
    private String descriptionDispute;
    private Timestamp datesStartDispute;
    private Timestamp dateEndDispute;
    private String photoDispute;
    private String resolutionText;
}

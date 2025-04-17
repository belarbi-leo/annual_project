package com.pa2aresgi.pa2a.DTO.create;

import com.pa2aresgi.pa2a.enumeratation.Status_ad_enum;
import com.pa2aresgi.pa2a.modele.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
@NoArgsConstructor
public class AdsDTOCreate {
    private Users id_user_creator;
    private Users id_user_accept;
    private Services id_svc;
    private Status_ad_enum status_ad;
    private Timestamp date_creation_ad;
    private Timestamp date_accept_ad;
    private Timestamp date_start_ad;
    private String street_start_ad;
    private String postal_code_start_ad;
    private String country_start_ad;
    private Timestamp date_end_ad;
    private String street_end_ad;
    private String postal_code_end_ad;
    private String country_end_ad;
    private String description_ad;
    private Float price_ad;
    private String photo_ad;
    /*private List<Requests_ads> requests_ads_list = new ArrayList<>();
    private List<Packages> packages_list = new ArrayList<>();
    private List<Opinions> opinions_list = new ArrayList<>();
    private List<Payments> payments_list = new ArrayList<>();
    private List<Disputes> disputes_list = new ArrayList<>();
    private Set<Routes> routes_set = new HashSet<>();*/
}

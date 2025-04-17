package com.pa2aresgi.pa2a.modele;

import com.pa2aresgi.pa2a.enumeratation.Status_req_annonce_enum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name="requests_ads")
@Getter
@Setter
@NoArgsConstructor
public class Requests_ads {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_req_ad;
    @ManyToOne
    @JoinColumn(name="id_user", nullable = false)
    private Users id_user;
    @ManyToOne
    @JoinColumn(name="id_ad", nullable = false)
    private Ads id_ad;
    @Column
    @Enumerated(EnumType.STRING)
    private Status_req_annonce_enum status_req_ad;
    @Column
    private Timestamp date_creation_req_ad;
    @Column
    private Timestamp date_accept_req_ad;
    @Column
    private Timestamp date_start_req_ad;
    /*@Column(length=255)
    private String street_start_req_annonce;*/
    @Column(length=255)
    private String location_start_req_ad;
    @Column(length = 255)
    private String suite_start_req_ad;
    @Column(length=255)
    private String locality_start_req_ad;
    @Column(length = 255)
    private String state_start_req_ad;
    @Column(length=20)
    private String postal_code_start_req_ad;
    @Column(length=100)
    private String country_start_req_ad;
    @Column
    private Timestamp date_end_req_ad;
    /*@Column(length=255)
    private String street_end_req_ad;*/
    @Column(length=255)
    private String location_end_req_ad;
    @Column(length = 255)
    private String suite_end_req_ad;
    @Column(length=255)
    private String locality_end_req_ad;
    @Column(length = 255)
    private String state_end_req_ad;
    @Column(length=20)
    private String postal_code_end_req_ad;
    @Column(length=100)
    private String country_end_req_ad;
    @Column(length=255)
    private String message_req_ad;
    @Column(columnDefinition="numeric(10,2)")
    private Float price_req_ad;
}

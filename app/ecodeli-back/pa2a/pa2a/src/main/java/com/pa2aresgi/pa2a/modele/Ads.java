package com.pa2aresgi.pa2a.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="ads")
@Getter
@Setter
@NoArgsConstructor
public class Ads {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_ad;
    @ManyToOne
    @JoinColumn(name="id_user", nullable = false)
    private Users id_user_creator;
    @ManyToOne
    @JoinColumn(name="id_user")
    private Users id_user_accept;
    @ManyToOne
    @JoinColumn(name="id_svc", nullable = false)
    private Services id_svc;
    @ManyToOne
    @JoinColumn(name="id_sub", nullable = false)
    private Subscriptions id_sub;
    @Column
    private Timestamp date_creation_ad;
    @Column
    private Timestamp date_accept_ad;
    @Column
    private Timestamp date_start_ad;
    @Column(length=255)
    private String street_start_ad;
    @Column(length=20)
    private String postal_code_start_ad;
    @Column(length=100)
    private String country_start_ad;
    @Column
    private Timestamp date_end_ad;
    @Column(length = 255)
    private String street_end_ad;
    @Column(length=20)
    private String postal_code_end_ad;
    @Column(length=100)
    private String country_end_ad;
    @Column(length = 255)
    private String description_ad;
    @Column(columnDefinition="numeric(10,2)")
    private Float price_ad;
    @Column
    private String photo_ad;
    @OneToMany(mappedBy = "ads")
    private List<Requests_ads> requests_ads_list = new ArrayList<>();
    @OneToMany(mappedBy = "ads")
    private List<Packages> packages_list = new ArrayList<>();
    @OneToMany(mappedBy = "ads")
    private List<Opinions> opinions_list = new ArrayList<>();
    @OneToMany(mappedBy = "ads")
    private List<Payments> payments_list = new ArrayList<>();
    @OneToMany(mappedBy = "ads")
    private List<Disputes> disputes_list = new ArrayList<>();
    @ManyToMany(mappedBy="ads")
    private Set<Routes> routes_set = new HashSet<>();
}

package com.pa2aresgi.pa2a.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pa2aresgi.pa2a.enumeratation.Svc_authorization;
import com.pa2aresgi.pa2a.enumeratation.Svc_category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="services")
@Getter
@Setter
@NoArgsConstructor
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_svc;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_admin_creator", nullable = false)
    private Users id_admin_creator;
    @Column
    private Timestamp date_creation_svc;
    @Column(length=100)
    private String name_svc;
    @Column
    @Enumerated(EnumType.STRING)
    private Svc_category category;
    @Column(length=50)
    private String subcategory;
    @Column
    @Enumerated(EnumType.STRING)
    private Svc_authorization auth;
    @OneToMany(mappedBy = "id_svc")
    @JsonIgnore
    private List<Services_docs> services_docs_list = new ArrayList<>();
    @OneToMany(mappedBy = "id_svc")
    @JsonIgnore
    private List<Materiels> materiels_list = new ArrayList<>();
    /*
    @OneToMany(mappedBy = "id_svc")
    @JsonIgnore
    private List<Authorizations> authorizations_list = new ArrayList<>();*/
    @OneToMany(mappedBy = "id_svc")
    @JsonIgnore
    private List<Requests_svc> requests_svc_list = new ArrayList<>();
    @OneToMany(mappedBy = "id_svc")
    @JsonIgnore
    private List<Ads> ads_list = new ArrayList<>();
}

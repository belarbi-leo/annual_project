package com.pa2aresgi.pa2a.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pa2aresgi.pa2a.enumeratation.AuthorizationSvcEnum;
import com.pa2aresgi.pa2a.enumeratation.CategorySvcEnum;
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
    @Column(name="id_svc")
    private Integer idSvc;
    @ManyToOne
    @JoinColumn(name="id_admin_creator", nullable = false)
    @JsonIgnore
    private Users adminCreator;
    @Column(name="date_creatin_svc")
    private Timestamp dateCreationSvc;
    @Column(name="name_svc", length=100)
    private String nameSvc;
    @Column(name="category")
    @Enumerated(EnumType.STRING)
    private CategorySvcEnum category;
    /*@Column(length=50)
    private String subcategory;*/
    @Column(name="auth")
    @Enumerated(EnumType.STRING)
    private AuthorizationSvcEnum auth;
    @OneToMany(mappedBy = "svc")
    @JsonIgnore
    private List<ServicesDocs> servicesDocsList = new ArrayList<>();
    @OneToMany(mappedBy = "svc")
    @JsonIgnore
    private List<Materiels> materielsList = new ArrayList<>();
    /*
    @OneToMany(mappedBy = "id_svc")
    @JsonIgnore
    private List<Authorizations> authorizations_list = new ArrayList<>();*/
    @OneToMany(mappedBy = "svc")
    @JsonIgnore
    private List<RequestsServices> requestsServicesList = new ArrayList<>();
    @OneToMany(mappedBy = "svc")
    @JsonIgnore
    private List<Ads> adsList = new ArrayList<>();
}

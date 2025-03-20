package com.pa2aresgi.pa2a.modele;

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
    @ManyToOne
    @JoinColumn(name="id_user", nullable = false)
    private Users id_admin_creator;
    @Column
    private Timestamp date_creation_svc;
    @Column(length=100)
    private String name_svc;
    @Column(length=50)
    private String category;
    @OneToMany(mappedBy = "services")
    private List<Services_docs> services_docs_list = new ArrayList<>();
    @OneToMany(mappedBy = "services")
    private List<Materiels> materiels_list = new ArrayList<>();
    @OneToMany(mappedBy = "services")
    private List<Authorizations> authorizations_list = new ArrayList<>();
    @OneToMany(mappedBy = "services")
    private List<Requests_svc> requests_svc_list = new ArrayList<>();
    @OneToMany(mappedBy = "services")
    private List<Ads> ads_list = new ArrayList<>();
}

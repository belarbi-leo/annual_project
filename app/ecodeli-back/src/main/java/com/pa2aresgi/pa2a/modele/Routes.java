package com.pa2aresgi.pa2a.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="routes")
@Getter
@Setter
@NoArgsConstructor
public class Routes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_route;
    @ManyToOne
    @JoinColumn(name="id_user", nullable = false)
    private Users id_user;
    @Column
    private Timestamp date_creation_route;
    @Column
    private Timestamp date_start_route;
    @Column(length=255)
    private String street_start_route;
    @Column(length=20)
    private String postal_code_start_route;
    @Column(length=100)
    private String country_start_route;
    @Column
    private Timestamp date_end_route;
    @Column(length=255)
    private String street_end_route;
    @Column(length=20)
    private String postal_code_end_route;
    @Column(length=100)
    private String country_end_route;
    @Column
    private String description_route;
    @Column
    private Integer step_route;
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "matches",
            joinColumns = @JoinColumn(name="id_route"),
            inverseJoinColumns = @JoinColumn(name="id_ad"))
    @JsonIgnore
    private Set<Ads> ads_set = new HashSet<>();
}

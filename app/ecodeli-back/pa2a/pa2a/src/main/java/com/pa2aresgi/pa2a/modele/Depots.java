package com.pa2aresgi.pa2a.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="depots")
@Getter
@Setter
@NoArgsConstructor
public class Depots {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_depot;
    @Column
    private Integer storage_capacity_depot;
    @Column(length = 255)
    private String street_depot;
    @Column(length = 20)    //pourquoi 20 si normalement c'est max 6 ?
    private String postal_code_depot;
    @Column(length = 100)
    private String country_depot;
}

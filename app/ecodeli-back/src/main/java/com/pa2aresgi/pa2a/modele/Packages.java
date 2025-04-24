package com.pa2aresgi.pa2a.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="packages")
@Getter
@Setter
@NoArgsConstructor
public class Packages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pack")
    private Integer idPack;
    @ManyToOne
    @JoinColumn(name="id_ad", nullable = false)
    private Ads ad;
    @Column(name="content_pack", columnDefinition="text")
    private String contentPack;
    @Column(name="quantity_pack")
    private Integer quantityPack;
    @Column(name="details_pack", length = 255)
    private String detailsPack;
    /*@Column
    @Enumerated(EnumType.STRING)
    private Type_pack_enum type_pack;*/
    @Column(name="weight_pack", columnDefinition="decimal(10,2)")
    private Float weightPack;
    @Column(name="length_pack")
    private Integer lengthPack;
    @Column(name="width_pack")
    private Integer widthPack;
    @Column(name="height_pack")
    private Integer heightPack;
    @Column(name="photo_pack", columnDefinition="text")
    private String photoPack;
    @Column(name="fragile")
    private Boolean fragile;
    @ManyToMany(mappedBy="packagesSet")
    @JsonIgnore
    private Set<Depots> depotsSet = new HashSet<>();
}

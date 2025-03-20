package com.pa2aresgi.pa2a.modele;

import com.pa2aresgi.pa2a.enumeratation.Type_pack_enum;
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
    private Integer id_pack;
    @ManyToOne
    @JoinColumn(name="id_ad", nullable = false)
    private Ads id_ad;
    @Column
    private String content_pack;
    @Column
    private Integer quantity_pack;
    @Column(length = 255)
    private String details_pack;
    @Column
    @Enumerated(EnumType.STRING)
    private Type_pack_enum type_pack;
    @Column(columnDefinition="numeric(10,2)")
    private Float weight_pack;
    @Column
    private Integer length_pack;
    @Column
    private Integer width_pack;
    @Column
    private Integer height_pack;
    @Column
    private String photo_pack;
    @Column
    private Boolean fragile;
    @ManyToMany(mappedBy="packages")
    private Set<Depots> depots_set = new HashSet<>();
}

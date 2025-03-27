package com.pa2aresgi.pa2a.modele.intermediairesInutiles;

import com.pa2aresgi.pa2a.modele.Ads;
import com.pa2aresgi.pa2a.modele.Routes;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="matches")
@Getter
@Setter
@NoArgsConstructor
public class Matches {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_match;
    @ManyToOne
    @JoinColumn(name="id_route", nullable = false)
    private Routes id_route;
    @ManyToOne
    @JoinColumn(name="id_ad", nullable = false)
    private Ads id_ad;
}

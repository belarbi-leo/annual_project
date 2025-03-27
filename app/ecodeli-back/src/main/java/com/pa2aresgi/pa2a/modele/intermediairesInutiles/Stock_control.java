package com.pa2aresgi.pa2a.modele.intermediairesInutiles;

import com.pa2aresgi.pa2a.modele.Depots;
import com.pa2aresgi.pa2a.modele.Packages;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="stock_control")
@Getter
@Setter
@NoArgsConstructor
public class Stock_control {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_stock;
    @ManyToOne
    @JoinColumn(name="id_package", nullable = false)
    private Packages id_package;
    @ManyToOne
    @JoinColumn(name="id_depot", nullable = false)
    private Depots id_depot;
}

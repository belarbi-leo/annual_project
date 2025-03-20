package com.pa2aresgi.pa2a.modele;

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
}

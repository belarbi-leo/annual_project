package com.pa2aresgi.pa2a.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="packages")
@Getter
@Setter
@NoArgsConstructor
public class Packages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pack;
}

package com.pa2aresgi.pa2a.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="opinions")
@Getter
@Setter
@NoArgsConstructor
public class Opinions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_opinion;
}

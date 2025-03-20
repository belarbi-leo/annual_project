package com.pa2aresgi.pa2a.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="subscriptions")
@Getter
@Setter
@NoArgsConstructor
public class Subscriptions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_sub;
    @Column(length = 50)
    private String name_sub;
    @Column
    private String description_sub;
}

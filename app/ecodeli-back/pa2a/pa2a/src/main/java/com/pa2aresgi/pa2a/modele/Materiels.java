package com.pa2aresgi.pa2a.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="materiels")
@Getter
@Setter
@NoArgsConstructor
public class Materiels {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_mat;
    @OneToOne
    @JoinColumn(name="id_svc", nullable = false)
    private Services id_svc;
    @Column(length=100)
    private String name_mat;
    @Column

}

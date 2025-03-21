package com.pa2aresgi.pa2a.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name="opinions")
@Getter
@Setter
@NoArgsConstructor
public class Opinions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_opinion;
    @ManyToOne
    @JoinColumn(name="id_ad", nullable = false)
    private Ads id_ad;
    @Column
    private short note_opinion;
    @Column(length = 255)
    private String title_opinion;
    @Column
    private String description_opinion;
    @Column
    private Timestamp date_opinion;
}

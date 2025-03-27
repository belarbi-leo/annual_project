package com.pa2aresgi.pa2a.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="services_docs")
@Getter
@Setter
@NoArgsConstructor
public class Services_docs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_doc_svc;
    @ManyToOne
    @JoinColumn(name="id_svc", nullable = false)
    private Services id_svc;
    @Column(length=100)
    private String name_doc;
}

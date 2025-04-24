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
public class ServicesDocs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_doc_svc")
    private Integer id_doc_svc;
    @ManyToOne
    @JoinColumn(name="id_svc", nullable = false)
    private Services svc;
    @Column(name="name_doc", length=100)
    private String nameDoc;
}

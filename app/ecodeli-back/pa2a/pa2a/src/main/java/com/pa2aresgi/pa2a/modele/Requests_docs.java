package com.pa2aresgi.pa2a.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="requests_docs")
@Getter
@Setter
@NoArgsConstructor
public class Requests_docs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_doc_req;
    @ManyToOne
    @JoinColumn(name="id_req_svc", nullable = false)
    private Requests_svc id_req_svc;
    @Column(length=100)
    private String doc_type_req;
    @Column
    private String doc_req;
}

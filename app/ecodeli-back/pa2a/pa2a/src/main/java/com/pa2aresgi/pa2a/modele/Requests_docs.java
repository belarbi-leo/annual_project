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
}

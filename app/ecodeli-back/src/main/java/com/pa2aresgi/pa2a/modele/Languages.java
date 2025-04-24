package com.pa2aresgi.pa2a.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="languages")
@Getter
@Setter
@NoArgsConstructor
public class Languages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_language")
    private Integer idLanguage;
    @Column(name="name", length = 30)
    private String name;
    @Column(name="iso", length = 2, unique = true, nullable = false)
    private String iso;
    @Column(name="available")
    private Boolean available;
    @OneToMany(mappedBy = "language")
    @JsonIgnore
    private List<Users> usersList = new ArrayList<>();
}

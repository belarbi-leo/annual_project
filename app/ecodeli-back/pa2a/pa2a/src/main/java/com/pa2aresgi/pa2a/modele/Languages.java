package com.pa2aresgi.pa2a.modele;

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
    private Integer id_language;
    @Column(length = 30)
    private String langue;
    @OneToMany(mappedBy = "languages")
    private List<Users> users_list = new ArrayList<>();
}

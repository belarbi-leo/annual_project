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
    private Integer id_langue;
    @Column(length = 30)
    private String langue;
    @Column(length = 2, unique = true, nullable = false)
    private String iso;
    @OneToMany(mappedBy = "id_user")
    @JsonIgnore
    private List<Users> users_list = new ArrayList<>();
}

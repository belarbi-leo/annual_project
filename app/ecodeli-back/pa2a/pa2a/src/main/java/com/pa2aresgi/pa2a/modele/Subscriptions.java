package com.pa2aresgi.pa2a.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(mappedBy = "id_sub")
    private List<Users> users_list = new ArrayList<>();
    @OneToMany(mappedBy = "id_sub")
    private List<Ads> ads_list = new ArrayList<>();
}

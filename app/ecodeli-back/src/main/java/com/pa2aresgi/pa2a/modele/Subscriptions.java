package com.pa2aresgi.pa2a.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(columnDefinition="numeric(10,2)")
    private Float price;
    @Column(columnDefinition="numeric(10,2)")
    private Float insurance;
    @Column
    private Integer shipping_reduction;
    @Column
    private Integer send_priority;
    @OneToMany(mappedBy = "id_subscription")
    @JsonIgnore
    private List<Users> users_list = new ArrayList<>();
    /*@OneToMany(mappedBy = "id_sub")
    @JsonIgnore
    private List<Ads> ads_list = new ArrayList<>();*/
}

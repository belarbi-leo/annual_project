package com.pa2aresgi.pa2a.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="authorizations")
@Getter
@Setter
@NoArgsConstructor
public class Authorizations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_auth;
    @ManyToOne
    @JoinColumn(name="id_svc", nullable = false)
    private Services id_svc;
    @ManyToOne
    @JoinColumn(name="id_user", nullable = false)
    private Users id_user;
    @Column
    private Timestamp date_granted;
}

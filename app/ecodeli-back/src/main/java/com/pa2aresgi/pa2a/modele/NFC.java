package com.pa2aresgi.pa2a.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="nfc")
@Getter
@Setter
@NoArgsConstructor
public class NFC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_card_nfc;
    @OneToOne
    @JoinColumn(name="id_user")
    private Users id_user;
}

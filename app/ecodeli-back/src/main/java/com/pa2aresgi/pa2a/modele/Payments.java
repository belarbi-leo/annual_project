package com.pa2aresgi.pa2a.modele;

import com.pa2aresgi.pa2a.enumeratation.Direction_payment_enum;
import com.pa2aresgi.pa2a.enumeratation.Status_payment_enum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name="payments")
@Getter
@Setter
@NoArgsConstructor
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_payment;
    @ManyToOne
    @JoinColumn(name="id_ad", nullable = false)
    private Ads id_ad;
    @Column
    @Enumerated(EnumType.STRING)
    private Direction_payment_enum direction_payment;
    @Column
    @Enumerated(EnumType.STRING)
    private Status_payment_enum status_payment;
    @Column
    private Timestamp date_payment;
    @Column
    private String bill_payment;
}

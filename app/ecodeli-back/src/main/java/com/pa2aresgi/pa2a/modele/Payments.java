package com.pa2aresgi.pa2a.modele;

import com.pa2aresgi.pa2a.enumeratation.DirectionPaymentEnum;
import com.pa2aresgi.pa2a.enumeratation.StatusPaymentEnum;
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
    @Column(name="id_payment")
    private Integer idPayment;
    @ManyToOne
    @JoinColumn(name="id_ad", nullable = false)
    private Ads ad;
    @Column(name="direction_payment")
    @Enumerated(EnumType.STRING)
    private DirectionPaymentEnum directionPayment;
    @Column(name="status_payment")
    @Enumerated(EnumType.STRING)
    private StatusPaymentEnum statusPayment;
    @Column(name="date_payment")
    private Timestamp datePayment;
    @Column(name="bill_payment", columnDefinition="text")
    private String billPayment;
}

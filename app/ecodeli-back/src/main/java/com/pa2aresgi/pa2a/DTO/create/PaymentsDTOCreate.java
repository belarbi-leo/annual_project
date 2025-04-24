package com.pa2aresgi.pa2a.DTO.create;

import com.pa2aresgi.pa2a.enumeratation.DirectionPaymentEnum;
import com.pa2aresgi.pa2a.enumeratation.StatusPaymentEnum;
import com.pa2aresgi.pa2a.modele.Ads;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class PaymentsDTOCreate {
    private Ads ad;
    private DirectionPaymentEnum directionPayment;
    private StatusPaymentEnum statusPayment;
    private Timestamp datePayment;
    private String billPayment;
}

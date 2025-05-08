package ecodeli.DTO.read;

import ecodeli.enumeratation.DirectionPaymentEnum;
import ecodeli.enumeratation.StatusPaymentEnum;
import ecodeli.modele.Ads;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class PaymentsDTORead {
    private Integer idPayment;
    private Ads ad;
    private DirectionPaymentEnum directionPayment;
    private StatusPaymentEnum statusPayment;
    private Timestamp datePayment;
    private String billPayment;
}

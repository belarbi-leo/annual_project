package ecodeli.DTO.create;

import ecodeli.enumeratation.DirectionPaymentEnum;
import ecodeli.enumeratation.StatusPaymentEnum;
import ecodeli.modele.Ads;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class PaymentsDTOCreate {
    @NotNull(message="Id ad must be informed")
    private Integer ad;
    @NotNull(message="direction payment is required")
    private DirectionPaymentEnum directionPayment;
    //DEFAULT 'pending'
    private StatusPaymentEnum statusPayment;
    //DEFAULT now()
    private Timestamp datePayment;
    private String billPayment;
}

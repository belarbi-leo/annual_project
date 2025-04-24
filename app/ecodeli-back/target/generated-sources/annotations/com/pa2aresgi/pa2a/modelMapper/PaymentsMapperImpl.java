package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.PaymentsDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.PaymentsDTORead;
import com.pa2aresgi.pa2a.modele.Payments;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-24T16:32:19+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class PaymentsMapperImpl implements PaymentsMapper {

    @Override
    public PaymentsDTORead toDtoRead(Payments payment) {
        if ( payment == null ) {
            return null;
        }

        PaymentsDTORead paymentsDTORead = new PaymentsDTORead();

        paymentsDTORead.setAd( payment.getAd() );
        paymentsDTORead.setBillPayment( payment.getBillPayment() );
        paymentsDTORead.setDatePayment( payment.getDatePayment() );
        paymentsDTORead.setDirectionPayment( payment.getDirectionPayment() );
        paymentsDTORead.setIdPayment( payment.getIdPayment() );
        paymentsDTORead.setStatusPayment( payment.getStatusPayment() );

        return paymentsDTORead;
    }

    @Override
    public Payments fromDtoCreate(PaymentsDTOCreate paymentDtoCreate) {
        if ( paymentDtoCreate == null ) {
            return null;
        }

        Payments payments = new Payments();

        payments.setAd( paymentDtoCreate.getAd() );
        payments.setBillPayment( paymentDtoCreate.getBillPayment() );
        payments.setDatePayment( paymentDtoCreate.getDatePayment() );
        payments.setDirectionPayment( paymentDtoCreate.getDirectionPayment() );
        payments.setStatusPayment( paymentDtoCreate.getStatusPayment() );

        return payments;
    }
}

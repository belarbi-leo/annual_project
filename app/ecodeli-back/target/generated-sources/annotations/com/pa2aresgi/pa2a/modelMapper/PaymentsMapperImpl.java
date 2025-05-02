package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.PaymentsDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.PaymentsDTORead;
import com.pa2aresgi.pa2a.modele.Payments;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-28T23:10:07+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.2 (Homebrew)"
)
@Component
public class PaymentsMapperImpl implements PaymentsMapper {

    @Override
    public PaymentsDTORead toDtoRead(Payments payment) {
        if ( payment == null ) {
            return null;
        }

        PaymentsDTORead paymentsDTORead = new PaymentsDTORead();

        paymentsDTORead.setIdPayment( payment.getIdPayment() );
        paymentsDTORead.setAd( payment.getAd() );
        paymentsDTORead.setDirectionPayment( payment.getDirectionPayment() );
        paymentsDTORead.setStatusPayment( payment.getStatusPayment() );
        paymentsDTORead.setDatePayment( payment.getDatePayment() );
        paymentsDTORead.setBillPayment( payment.getBillPayment() );

        return paymentsDTORead;
    }

    @Override
    public Payments fromDtoCreate(PaymentsDTOCreate paymentDtoCreate) {
        if ( paymentDtoCreate == null ) {
            return null;
        }

        Payments payments = new Payments();

        payments.setAd( paymentDtoCreate.getAd() );
        payments.setDirectionPayment( paymentDtoCreate.getDirectionPayment() );
        payments.setStatusPayment( paymentDtoCreate.getStatusPayment() );
        payments.setDatePayment( paymentDtoCreate.getDatePayment() );
        payments.setBillPayment( paymentDtoCreate.getBillPayment() );

        return payments;
    }
}

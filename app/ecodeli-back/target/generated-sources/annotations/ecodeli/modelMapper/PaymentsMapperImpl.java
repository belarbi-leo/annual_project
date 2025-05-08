package ecodeli.modelMapper;

import ecodeli.DTO.create.PaymentsDTOCreate;
import ecodeli.DTO.read.PaymentsDTORead;
import ecodeli.modele.Payments;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-08T15:25:52+0200",
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

        payments.setBillPayment( paymentDtoCreate.getBillPayment() );
        payments.setDatePayment( paymentDtoCreate.getDatePayment() );
        payments.setDirectionPayment( paymentDtoCreate.getDirectionPayment() );
        payments.setStatusPayment( paymentDtoCreate.getStatusPayment() );

        return payments;
    }

    @Override
    public Payments updatePaymentFromDtoCreate(PaymentsDTOCreate paymentDtoCreate, Payments payment) {
        if ( paymentDtoCreate == null ) {
            return payment;
        }

        if ( paymentDtoCreate.getBillPayment() != null ) {
            payment.setBillPayment( paymentDtoCreate.getBillPayment() );
        }
        if ( paymentDtoCreate.getDatePayment() != null ) {
            payment.setDatePayment( paymentDtoCreate.getDatePayment() );
        }
        if ( paymentDtoCreate.getDirectionPayment() != null ) {
            payment.setDirectionPayment( paymentDtoCreate.getDirectionPayment() );
        }
        if ( paymentDtoCreate.getStatusPayment() != null ) {
            payment.setStatusPayment( paymentDtoCreate.getStatusPayment() );
        }

        return payment;
    }
}

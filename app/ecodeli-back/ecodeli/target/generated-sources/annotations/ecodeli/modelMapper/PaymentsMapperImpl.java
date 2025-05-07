package ecodeli.modelMapper;

import ecodeli.DTO.create.PaymentsDTOCreate;
import ecodeli.DTO.read.PaymentsDTORead;
import ecodeli.modele.Payments;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-07T13:42:24+0200",
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

        payments.setDirectionPayment( paymentDtoCreate.getDirectionPayment() );
        payments.setStatusPayment( paymentDtoCreate.getStatusPayment() );
        payments.setDatePayment( paymentDtoCreate.getDatePayment() );
        payments.setBillPayment( paymentDtoCreate.getBillPayment() );

        return payments;
    }

    @Override
    public Payments updatePaymentFromDtoCreate(PaymentsDTOCreate paymentDtoCreate, Payments payment) {
        if ( paymentDtoCreate == null ) {
            return payment;
        }

        if ( paymentDtoCreate.getDirectionPayment() != null ) {
            payment.setDirectionPayment( paymentDtoCreate.getDirectionPayment() );
        }
        if ( paymentDtoCreate.getStatusPayment() != null ) {
            payment.setStatusPayment( paymentDtoCreate.getStatusPayment() );
        }
        if ( paymentDtoCreate.getDatePayment() != null ) {
            payment.setDatePayment( paymentDtoCreate.getDatePayment() );
        }
        if ( paymentDtoCreate.getBillPayment() != null ) {
            payment.setBillPayment( paymentDtoCreate.getBillPayment() );
        }

        return payment;
    }
}

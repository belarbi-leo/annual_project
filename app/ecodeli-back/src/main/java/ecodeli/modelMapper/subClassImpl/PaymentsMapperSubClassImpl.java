package ecodeli.modelMapper.subClassImpl;

import ecodeli.DTO.create.PaymentsDTOCreate;
import ecodeli.DTO.read.PaymentsDTORead;
import ecodeli.modelMapper.PaymentsMapper;
import ecodeli.modele.Ads;
import ecodeli.modele.Payments;
import ecodeli.repository.AdsRepository;
import ecodeli.tools.ResponseErrorV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentsMapperSubClassImpl {
    private final PaymentsMapper paymentsMapper;
    private final AdsRepository adsRepository;

    public PaymentsDTORead toDtoRead(Payments payment){
        return paymentsMapper.toDtoRead(payment);
    }

    public Object fromDtoCreate(PaymentsDTOCreate paymentDTOCreate){
        Payments payment = paymentsMapper.fromDtoCreate(paymentDTOCreate);
        Optional<Ads> ad = adsRepository.findById(paymentDTOCreate.getAd());
        ResponseErrorV2 retourNeg = new ResponseErrorV2();
        if(ad.isEmpty()) retourNeg.add("ad","Id ad not found !");
        else payment.setAd(ad.get());
        if (!retourNeg.getErrors().isEmpty())
            return retourNeg;
        else
            return payment;
    }

    public Object updatePaymentFromDtoCreate(PaymentsDTOCreate paymentDTOCreate, Payments payment){
        Payments pay = paymentsMapper.updatePaymentFromDtoCreate(paymentDTOCreate, payment);
        ResponseErrorV2 retourNeg = new ResponseErrorV2();
        if (paymentDTOCreate.getAd() != null){
            Optional<Ads> ad = adsRepository.findById(paymentDTOCreate.getAd());
            if (ad.isEmpty()) retourNeg.add("ad", "Id ad not found !");
            else pay.setAd(ad.get());
        }
        if (!retourNeg.getErrors().isEmpty())
            return retourNeg;
        else
            return pay;
    }
}

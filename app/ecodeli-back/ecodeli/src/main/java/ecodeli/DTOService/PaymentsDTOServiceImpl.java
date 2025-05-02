package ecodeli.DTOService;

import ecodeli.DTO.create.PaymentsDTOCreate;
import ecodeli.DTO.read.PaymentsDTORead;
import ecodeli.enumeratation.StatusPaymentEnum;
import ecodeli.modelMapper.subClassImpl.PaymentsMapperSubClassImpl;
import ecodeli.modele.Payments;
import ecodeli.repository.PaymentsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentsDTOServiceImpl implements PaymentsDTOService {
    private PaymentsRepository paymentsRepository;
    @Autowired
    private PaymentsMapperSubClassImpl paymentsMapper;

    @Override
    public Object create(PaymentsDTOCreate paymentDtoCreate){
        if (paymentDtoCreate.getStatusPayment() == null) paymentDtoCreate.setStatusPayment(StatusPaymentEnum.pending);
        if (paymentDtoCreate.getDatePayment() == null) paymentDtoCreate.setDatePayment(Timestamp.valueOf(LocalDateTime.now()));

        Object obj = paymentsMapper.fromDtoCreate(paymentDtoCreate);
        if (obj instanceof Payments)
            return paymentsMapper.toDtoRead(paymentsRepository.save((Payments) obj));
        else
            return obj;
    }

    @Override
    public List<PaymentsDTORead> readAll(){
        return paymentsRepository.findAll().stream().map(paymentsMapper::toDtoRead).toList();
    }

    @Override
    public List<PaymentsDTORead> readAll(Sort sort){
        return paymentsRepository.findAll(sort).stream().map(paymentsMapper::toDtoRead).toList();
    }

    @Override
    public Slice<PaymentsDTORead> readAll(Pageable pageParam){
        return paymentsRepository.findAll(pageParam).map(paymentsMapper::toDtoRead);
    }

    @Override
    public Optional<PaymentsDTORead> findById(Integer id){
        return paymentsRepository.findById(id).map(paymentsMapper::toDtoRead);
    }

    @Override
    public Optional<Object> update(Integer id, PaymentsDTOCreate paymentDTOCreate){
        return paymentsRepository.findById(id).map(payment -> {
            Object obj = paymentsMapper.updatePaymentFromDtoCreate(paymentDTOCreate, payment);
            if (obj instanceof Payments)
                return paymentsMapper.toDtoRead(paymentsRepository.save((Payments) obj));
            else
                return obj;
        });
    }

    @Override
    public Boolean deleteById(Integer id){
        if (paymentsRepository.existsById(id)){
            paymentsRepository.deleteById(id);
            return true;
        } else
            return false;
    }
}

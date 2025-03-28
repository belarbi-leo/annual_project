package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.modele.Payments;
import com.pa2aresgi.pa2a.repository.PaymentsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentsServiceImpl implements PaymentsService {
    private PaymentsRepository paymentsRepository;

    @Override
    public Payments create(Payments payment) {
        return paymentsRepository.save(payment);
    }

    @Override
    public List<Payments> readAll() {
        return paymentsRepository.findAll();
    }

    @Override
    public Payments findById(Integer id) {
        if (paymentsRepository.findById(id).isPresent()){
            return paymentsRepository.findById(id).get();
        } else {
            throw new RuntimeException("Payment not found ! ");
        }
    }

    @Override
    public Payments update(Integer id, Payments payment) {
        return paymentsRepository.findById(id).map(pay -> {
            pay.setId_ad(payment.getId_ad());
            pay.setDirection_payment(payment.getDirection_payment());
            pay.setStatus_payment(payment.getStatus_payment());
            pay.setDate_payment(payment.getDate_payment());
            pay.setBill_payment(payment.getBill_payment());
            return paymentsRepository.save(pay);
        }).orElseThrow(() -> new RuntimeException("Payment not found !"));
    }

    @Override
    public String deleteById(Integer id) {
        paymentsRepository.deleteById(id);
        return "Payment deleted !";
    }
}
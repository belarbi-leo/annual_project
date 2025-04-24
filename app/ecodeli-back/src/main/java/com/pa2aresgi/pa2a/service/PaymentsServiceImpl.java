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
    public List<Payments> readAllOrderById() {
        return paymentsRepository.findAllOrderById_payment();
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
            pay.setAd(payment.getAd());
            pay.setDirectionPayment(payment.getDirectionPayment());
            pay.setStatusPayment(payment.getStatusPayment());
            pay.setDatePayment(payment.getDatePayment());
            pay.setBillPayment(payment.getBillPayment());
            return paymentsRepository.save(pay);
        }).orElseThrow(() -> new RuntimeException("Payment not found !"));
    }

    @Override
    public String deleteById(Integer id) {
        paymentsRepository.deleteById(id);
        return "Payment deleted !";
    }
}
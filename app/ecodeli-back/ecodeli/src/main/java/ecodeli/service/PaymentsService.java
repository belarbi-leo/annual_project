package ecodeli.service;

import ecodeli.modele.Payments;

import java.util.List;

public interface PaymentsService {
    Payments create(Payments payment);

    List<Payments> readAll();

    List<Payments> readAllOrderById();

    Payments findById(Integer id);

    Payments update(Integer id, Payments payment);

    String deleteById(Integer id);
}

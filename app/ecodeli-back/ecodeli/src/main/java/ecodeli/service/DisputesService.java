package ecodeli.service;

import ecodeli.modele.Disputes;

import java.util.List;

public interface DisputesService {
    Disputes create(Disputes dispute);

    List<Disputes> readAll();

    List<Disputes> readAllOrderById();

    Disputes findById(Integer id);

    Disputes update(Integer id, Disputes dispute);

    String deleteById(Integer id);
}

package ecodeli.service;

import ecodeli.modele.Packages;

import java.util.List;

public interface PackagesService {
    Packages create(Packages pack);

    List<Packages> readAll();

    List<Packages> readAllOrderById();

    Packages findById(Integer id);

    Packages update(Integer id, Packages pack);

    String deleteById(Integer id);
}

package ecodeli.service;

import ecodeli.modele.Opinions;

import java.util.List;

public interface OpinionsService {
    Opinions create(Opinions opinion);

    List<Opinions> readAll();

    List<Opinions> readAllOrderById();

    Opinions findById(Integer id);

    Opinions update(Integer id, Opinions opinion);

    String deleteById(Integer id);
}

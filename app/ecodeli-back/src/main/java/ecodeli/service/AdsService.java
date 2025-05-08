package ecodeli.service;

import ecodeli.modele.Ads;

import java.util.List;

public interface AdsService {

    Ads create(Ads ad);

    List<Ads> readAll();

    List<Ads> readAllOrderById();

    Ads findById(Integer id);

    Ads update(Integer id, Ads ad);

    String deleteById(Integer id);
}

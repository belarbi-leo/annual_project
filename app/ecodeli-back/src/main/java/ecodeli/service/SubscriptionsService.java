package ecodeli.service;

import ecodeli.modele.Subscriptions;

import java.util.List;

public interface SubscriptionsService {
    Subscriptions create(Subscriptions subscription);

    List<Subscriptions> readAll();

    List<Subscriptions> readAllOrderById();

    Subscriptions findById(Integer id);

    Subscriptions update(Integer id, Subscriptions subscription);

    String deleteById(Integer id);
}

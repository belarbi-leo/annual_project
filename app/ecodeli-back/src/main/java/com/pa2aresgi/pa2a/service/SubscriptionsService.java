package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.modele.Subscriptions;

import java.util.List;

public interface SubscriptionsService {
    Subscriptions create(Subscriptions subscription);

    List<Subscriptions> readAll();
    Subscriptions findById(Integer id);

    Subscriptions update(Integer id, Subscriptions subscription);

    String deleteById(Integer id);
}

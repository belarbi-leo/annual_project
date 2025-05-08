package ecodeli.service;

import ecodeli.modele.Subscriptions;
import ecodeli.repository.SubscriptionsRepository;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SubscriptionsServiceImpl implements SubscriptionsService{

    private SubscriptionsRepository subscriptionsRepository;

    @Override
    public Subscriptions create(Subscriptions subscription) {
        return subscriptionsRepository.save(subscription);
    }

    @Override
    public List<Subscriptions> readAll() {
        return subscriptionsRepository.findAll();
    }

    @Override
    public List<Subscriptions> readAllOrderById() {
        return subscriptionsRepository.findAllOrderById_sub();
    }

    @Override
    public Subscriptions findById(Integer id) {
        if (subscriptionsRepository.findById(id).isPresent()){
            return subscriptionsRepository.findById(id).get();
        } else {
            throw new RuntimeException("Subscription not found ! ");
        }
    }

    @Override
    public Subscriptions update(Integer id, Subscriptions subscription) {
        return subscriptionsRepository.findById(id).map(sub -> {
            sub.setNameSub(subscription.getNameSub());
            sub.setDescriptionSub(subscription.getDescriptionSub());
            sub.setPrice(subscription.getPrice());
            sub.setInsurance(subscription.getInsurance());
            sub.setShippingReduction(subscription.getShippingReduction());
            sub.setSendPriority(subscription.getSendPriority());
            sub.setTargetAudience(subscription.getTargetAudience());
            sub.setActive(subscription.getActive());
            return subscriptionsRepository.save(sub);
        }).orElseThrow(() -> new RuntimeException("Subscription not found !"));
    }

    @Override
    public String deleteById(Integer id) {
        subscriptionsRepository.deleteById(id);
        return "Subscription deleted!";
    }
}

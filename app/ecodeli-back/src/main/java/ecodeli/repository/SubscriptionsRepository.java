package ecodeli.repository;

import ecodeli.modele.Subscriptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubscriptionsRepository extends JpaRepository<Subscriptions, Integer> {
    @Query("select sub from Subscriptions sub order by sub.idSubscription")
    List<Subscriptions> findAllOrderById_sub();
}

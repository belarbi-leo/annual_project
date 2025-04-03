package com.pa2aresgi.pa2a.repository;

import com.pa2aresgi.pa2a.modele.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentsRepository extends JpaRepository<Payments, Integer> {
    @Query("select pay from Payments pay order by pay.id_payment")
    List<Payments> findAllOrderById_payment();
}

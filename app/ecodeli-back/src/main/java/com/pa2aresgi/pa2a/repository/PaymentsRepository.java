package com.pa2aresgi.pa2a.repository;

import com.pa2aresgi.pa2a.modele.Payments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentsRepository extends JpaRepository<Payments, Integer> {
}

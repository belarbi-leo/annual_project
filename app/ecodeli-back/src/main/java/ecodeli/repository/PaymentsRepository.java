package ecodeli.repository;

import ecodeli.modele.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentsRepository extends JpaRepository<Payments, Integer> {
    @Query("select pay from Payments pay order by pay.idPayment")
    List<Payments> findAllOrderById_payment();
}

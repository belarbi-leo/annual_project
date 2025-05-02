package ecodeli.repository;

import ecodeli.modele.RequestsServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RequestsServicesRepository extends JpaRepository<RequestsServices,Integer> {
    @Query("select reqSvc from RequestsServices reqSvc order by reqSvc.idReqSvc")
    List<RequestsServices> findAllOrderById_req_svc();
}

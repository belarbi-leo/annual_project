package ecodeli.repository;

import ecodeli.enumeratation.AuthorizationSvcEnum;
import ecodeli.modele.Services;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface ServicesRepository extends JpaRepository<Services,Integer> {
    @Query("select svc from Services svc order by svc.idSvc")
    List<Services> findAllOrderById_svc();

    List<Services> findAllByAuth(AuthorizationSvcEnum auth);

    @Query("select svc from Services svc where svc.auth = ?1 order by svc.idSvc")
    List<Services> findAllByAuthOrderById_svc(AuthorizationSvcEnum auth);

    List<Services> findAllByAuthIn(Set<AuthorizationSvcEnum> auths);

    List<Services> findAllByAuthIn(Set<AuthorizationSvcEnum> auths, Sort sort);

    Slice<Services> findAllByAuthIn(Set<AuthorizationSvcEnum> auths, Pageable pageParam);
}

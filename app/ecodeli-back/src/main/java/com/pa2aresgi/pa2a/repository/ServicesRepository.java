package com.pa2aresgi.pa2a.repository;

<<<<<<< HEAD
import com.pa2aresgi.pa2a.enumeratation.AuthorizationSvcEnum;
=======
import com.pa2aresgi.pa2a.enumeratation.Svc_authorization;
>>>>>>> d76060e (feat: signin)
import com.pa2aresgi.pa2a.modele.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServicesRepository extends JpaRepository<Services,Integer> {
    @Query("select svc from Services svc order by svc.idSvc")
    List<Services> findAllOrderById_svc();

    List<Services> findAllByAuth(AuthorizationSvcEnum auth);

<<<<<<< HEAD
    @Query("select svc from Services svc where svc.auth = ?1 order by svc.idSvc")
    List<Services> findAllByRoleOrderById_svc(AuthorizationSvcEnum auth);
}
=======
    @Query("select svc from Services svc where svc.auth = ?1 order by svc.id_svc")
    List<Services> findAllByRoleOrderById_svc(Svc_authorization auth);
}
>>>>>>> d76060e (feat: signin)

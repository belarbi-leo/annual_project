package com.pa2aresgi.pa2a.service;

<<<<<<< HEAD
import com.pa2aresgi.pa2a.enumeratation.AuthorizationSvcEnum;
=======
import com.pa2aresgi.pa2a.enumeratation.Svc_authorization;
>>>>>>> d76060e (feat: signin)
import com.pa2aresgi.pa2a.modele.Services;

import java.util.List;

public interface ServicesService {
    Services create(Services service);

    List<Services> readAll();

    List<Services> readAllOrderById();

<<<<<<< HEAD
    List<Services> readAllByAuth(AuthorizationSvcEnum auth);

    public List<Services> readAllByAuthOrderById(AuthorizationSvcEnum auth);
=======
    List<Services> readAllByAuth(Svc_authorization auth);

    public List<Services> readAllByAuthOrderById(Svc_authorization auth);
>>>>>>> d76060e (feat: signin)

    Services findById(Integer id);

    Services update(Integer id, Services service);

    String deleteById(Integer id);
}
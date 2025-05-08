package ecodeli.service;
/*
import ecodeli.modele.intermediairesInutiles.Authorizations;
import ecodeli.repository.AuthorizationsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class AuthorizationsServiceImpl implements AuthorizationsService {

    private AuthorizationsRepository authorizationsRepository;

    @Override
    public Authorizations create(Authorizations authorization) {
        return authorizationsRepository.save(authorization);
    }

    @Override
    public List<Authorizations> readAll() {
        return authorizationsRepository.findAll();
    }

    @Override
    public List<Authorizations> readAllOrderById() {
        return authorizationsRepository.findAllOrderById_auth();
    }

    @Override
    public Authorizations findById(Integer id) {
        if (authorizationsRepository.findById(id).isPresent()){
            return authorizationsRepository.findById(id).get();
        } else {
            throw new RuntimeException("Authorization not found ! ");
        }
    }

    @Override
    public Authorizations update(Integer id, Authorizations authorization) {
        return authorizationsRepository.findById(id).map(auth -> {
            auth.setId_svc(authorization.getId_svc());
            auth.setId_user(authorization.getId_user());
            auth.setDate_granted(auth.getDate_granted());
            return authorizationsRepository.save(auth);
        }).orElseThrow(() -> new RuntimeException("Authorization not found !"));
    }

    @Override
    public String deleteById(Integer id) {
        return "Authorization deleted!";
    }
}
*/
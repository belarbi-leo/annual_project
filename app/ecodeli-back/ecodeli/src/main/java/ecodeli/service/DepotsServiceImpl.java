package ecodeli.service;

import ecodeli.modele.Depots;
import ecodeli.repository.DepotsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepotsServiceImpl implements DepotsService {

    private DepotsRepository depotsRepository;

    @Override
    public Depots create(Depots depot) {
        return depotsRepository.save(depot);
    }

    @Override
    public List<Depots> readAll() {
        return depotsRepository.findAll();
    }

    @Override
    public List<Depots> readAllOrderById() {
        return depotsRepository.findAllOrderById_depot();
    }

    @Override
    public Depots findById(Integer id) {
        if (depotsRepository.findById(id).isPresent()){
            return depotsRepository.findById(id).get();
        } else {
            throw new RuntimeException("Depot not found ! ");
        }
    }

    @Override
    public Depots update(Integer id, Depots depot) {
        return depotsRepository.findById(id).map(dep -> {
            dep.setStorageCapacityDepot(depot.getStorageCapacityDepot());
            //dep.setStreet_depot(depot.getStreet_depot());
            dep.setLocation(depot.getLocation());
            dep.setSuite(depot.getSuite());
            dep.setLocality(depot.getLocality());
            dep.setState(depot.getState());
            dep.setPostalCode(depot.getPostalCode());
            dep.setCountry(depot.getCountry());
            dep.setLatitude(depot.getLatitude());
            dep.setLongitude(depot.getLongitude());
            return depotsRepository.save(dep);
        }).orElseThrow(() -> new RuntimeException("Depot not found !"));
    }

    @Override
    public String deleteById(Integer id) {
        return "Depot deleted!";
    }
}

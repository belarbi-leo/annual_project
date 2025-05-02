package ecodeli.service;

import ecodeli.modele.Packages;
import ecodeli.repository.PackagesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PackagesServiceImpl implements PackagesService {
    private PackagesRepository packagesRepository;

    @Override
    public Packages create(Packages pack) {
        return packagesRepository.save(pack);
    }

    @Override
    public List<Packages> readAll() {
        return packagesRepository.findAll();
    }

    @Override
    public List<Packages> readAllOrderById() {
        return packagesRepository.findAllOrderById_pack();
    }

    @Override
    public Packages findById(Integer id) {
        if (packagesRepository.findById(id).isPresent()){
            return packagesRepository.findById(id).get();
        } else {
            throw new RuntimeException("Package not found ! ");
        }
    }

    @Override
    public Packages update(Integer id, Packages pack) {
        return packagesRepository.findById(id).map(pack1 -> {
            pack1.setAd(pack.getAd());
            pack1.setContentPack(pack.getContentPack());
            pack1.setQuantityPack(pack.getQuantityPack());
            pack1.setDetailsPack(pack.getDetailsPack());
            //pack1.setType_pack(pack.getType_pack());
            pack1.setWeightPack(pack.getWeightPack());
            pack1.setLengthPack(pack.getLengthPack());
            pack1.setWidthPack(pack.getWidthPack());
            pack1.setHeightPack(pack.getHeightPack());
            pack1.setPhotoPack(pack.getPhotoPack());
            pack1.setFragile(pack.getFragile());/*
            pack1.setDepots_set(pack.getDepots_set());*/
            return packagesRepository.save(pack1);
        }).orElseThrow(() -> new RuntimeException("Package not found !"));
    }

    @Override
    public String deleteById(Integer id) {
        packagesRepository.deleteById(id);
        return "Package deleted !";
    }
}
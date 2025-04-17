package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.modele.Packages;
import com.pa2aresgi.pa2a.repository.PackagesRepository;
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
            pack1.setId_ad(pack.getId_ad());
            pack1.setContent_pack(pack.getContent_pack());
            pack1.setQuantity_pack(pack.getQuantity_pack());
            pack1.setDetails_pack(pack.getDetails_pack());
            //pack1.setType_pack(pack.getType_pack());
            pack1.setWeight_pack(pack.getWeight_pack());
            pack1.setLength_pack(pack.getLength_pack());
            pack1.setWidth_pack(pack.getWidth_pack());
            pack1.setHeight_pack(pack.getHeight_pack());
            pack1.setPhoto_pack(pack.getPhoto_pack());
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
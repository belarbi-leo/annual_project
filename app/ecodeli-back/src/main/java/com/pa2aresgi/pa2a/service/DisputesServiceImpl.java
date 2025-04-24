package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.modele.Disputes;
import com.pa2aresgi.pa2a.repository.DisputesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DisputesServiceImpl implements DisputesService {

    private DisputesRepository disputesRepository;

    @Override
    public Disputes create(Disputes dispute) {
        return disputesRepository.save(dispute);
    }

    @Override
    public List<Disputes> readAll() {
        return disputesRepository.findAll();
    }

    @Override
    public List<Disputes> readAllOrderById() {
        return disputesRepository.findAllOrderById_dispute();
    }

    @Override
    public Disputes findById(Integer id) {
        if (disputesRepository.findById(id).isPresent()){
            return disputesRepository.findById(id).get();
        } else {
            throw new RuntimeException("Dispute not found ! ");
        }
    }

    @Override
    public Disputes update(Integer id, Disputes dispute) {
        return disputesRepository.findById(id).map(disp -> {
            disp.setAd(dispute.getAd());
            disp.setUser(dispute.getUser());
            disp.setDateStatusDispute(dispute.getDateStatusDispute());
            disp.setStatusDispute(dispute.getStatusDispute());
            disp.setDescriptionDispute(dispute.getDescriptionDispute());
            disp.setDateStartDispute(dispute.getDateStartDispute());
            disp.setDateEndDispute(dispute.getDateEndDispute());
            disp.setPhotoDispute(dispute.getPhotoDispute());
            disp.setResolutionText(dispute.getResolutionText());
            return disputesRepository.save(disp);
        }).orElseThrow(() -> new RuntimeException("Dispute not found !"));
    }

    @Override
    public String deleteById(Integer id) {
        return "Dispute deleted!";
    }
}
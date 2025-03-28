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
            disp.setId_ad(dispute.getId_ad());
            disp.setId_user(dispute.getId_user());
            disp.setDate_status_dispute(dispute.getDate_status_dispute());
            disp.setStatus_dispute(dispute.getStatus_dispute());
            disp.setDescription_dispute(dispute.getDescription_dispute());
            disp.setDate_start_dispute(dispute.getDate_start_dispute());
            disp.setDate_end_dispute(dispute.getDate_end_dispute());
            disp.setPhoto_dispute(dispute.getPhoto_dispute());
            return disputesRepository.save(disp);
        }).orElseThrow(() -> new RuntimeException("Dispute not found !"));
    }

    @Override
    public String deleteById(Integer id) {
        return "Dispute deleted!";
    }
}
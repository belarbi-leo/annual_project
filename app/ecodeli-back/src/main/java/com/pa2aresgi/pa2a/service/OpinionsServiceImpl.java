package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.modele.Opinions;
import com.pa2aresgi.pa2a.repository.OpinionsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OpinionsServiceImpl implements OpinionsService {
    private OpinionsRepository opinionsRepository;

    @Override
    public Opinions create(Opinions opinion) {
        return opinionsRepository.save(opinion);
    }

    @Override
    public List<Opinions> readAll() {
        return opinionsRepository.findAll();
    }

    @Override
    public List<Opinions> readAllOrderById() {
        return opinionsRepository.findAllOrderById_opinion();
    }

    @Override
    public Opinions findById(Integer id) {
        if (opinionsRepository.findById(id).isPresent()){
            return opinionsRepository.findById(id).get();
        } else {
            throw new RuntimeException("Opinion not found ! ");
        }
    }

    @Override
    public Opinions update(Integer id, Opinions opinion) {
        return opinionsRepository.findById(id).map(op -> {
            op.setId_ad(opinion.getId_ad());
            op.setNote_opinion(opinion.getNote_opinion());
            op.setTitle_opinion(opinion.getTitle_opinion());
            op.setDescription_opinion(opinion.getDescription_opinion());
            op.setDate_opinion(opinion.getDate_opinion());
            return opinionsRepository.save(op);
        }).orElseThrow(() -> new RuntimeException("Opinion not found !"));
    }

    @Override
    public String deleteById(Integer id) {
        opinionsRepository.deleteById(id);
        return "Opinion deleted !";
    }
}

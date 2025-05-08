package ecodeli.service;

import ecodeli.modele.Opinions;
import ecodeli.repository.OpinionsRepository;
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
            op.setAd(opinion.getAd());
            op.setNoteOpinion(opinion.getNoteOpinion());
            op.setTitleOpinion(opinion.getTitleOpinion());
            op.setDescriptionOpinion(opinion.getDescriptionOpinion());
            op.setDateOpinion(opinion.getDateOpinion());
            return opinionsRepository.save(op);
        }).orElseThrow(() -> new RuntimeException("Opinion not found !"));
    }

    @Override
    public String deleteById(Integer id) {
        opinionsRepository.deleteById(id);
        return "Opinion deleted !";
    }
}

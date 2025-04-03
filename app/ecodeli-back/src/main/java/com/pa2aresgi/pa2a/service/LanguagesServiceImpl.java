package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.modele.Languages;
import com.pa2aresgi.pa2a.repository.LanguagesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LanguagesServiceImpl implements LanguagesService {

    private LanguagesRepository languagesRepository;

    @Override
    public Languages create(Languages language) {
        return languagesRepository.save(language);
    }

    @Override
    public List<Languages> readAll() {
        return languagesRepository.findAll();
    }

    @Override
    public List<Languages> readAllOrderById() {
        return languagesRepository.findAllOrderById_langue();
    }

    @Override
    public Languages findById(Integer id) {
        if (languagesRepository.findById(id).isPresent()){
            return languagesRepository.findById(id).get();
        } else {
            throw new RuntimeException("Language not found ! ");
        }
    }

    @Override
    public Languages update(Integer id, Languages language) {
        return languagesRepository.findById(id).map(langue -> {
            langue.setLangue(language.getLangue());
            langue.setIso(language.getIso());
            return languagesRepository.save(langue);
        }).orElseThrow(() -> new RuntimeException("Language not found !"));
    }

    @Override
    public String deleteById(Integer id) {
        languagesRepository.deleteById(id);
        return "Language deleted !";
    }
}

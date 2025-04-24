package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.modele.Languages;
import com.pa2aresgi.pa2a.repository.LanguagesRepository;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
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

    /*@Override
    public List<Languages> readAll() {
        return languagesRepository.findAll();
    }*/
    @Override
    public List<Languages> readAll() {
        return languagesRepository.findAll();
    }

    @Override
    public List<Languages> readAll(Sort sort) {
        return languagesRepository.findAll(sort);
    }

    @Override
    public Slice<Languages> readAll(Pageable pageParam){
        return languagesRepository.findAll(pageParam);
    }

    @Override
    public List<Languages> readAllOrderById() {
        return languagesRepository.findAllOrderById_language();
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
            langue.setName(language.getName());
            langue.setIso(language.getIso());
            langue.setAvailable(language.getAvailable());
            return languagesRepository.save(langue);
        }).orElseThrow(() -> new RuntimeException("Language not found !"));
    }

    @Override
    public String deleteById(Integer id) {
        languagesRepository.deleteById(id);
        return "Language deleted !";
    }
}

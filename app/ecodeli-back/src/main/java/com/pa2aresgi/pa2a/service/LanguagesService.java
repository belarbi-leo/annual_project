package com.pa2aresgi.pa2a.service;

import com.pa2aresgi.pa2a.modele.Languages;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface LanguagesService {

    Languages create(Languages language);

    List<Languages> readAll();

    List<Languages> readAll(Sort sort);

    Slice<Languages> readAll(Pageable pageParam);

    List<Languages> readAllOrderById();

    Languages findById(Integer id);

    Languages update(Integer id, Languages language);

    String deleteById(Integer id);
}

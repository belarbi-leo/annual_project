package com.pa2aresgi.pa2a.repository;

import com.pa2aresgi.pa2a.modele.Languages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LanguagesRepository extends JpaRepository<Languages, Integer>/*, PagingAndSortingRepository<Languages, Integer> */{
    @Query("select language from Languages language order by language.idLanguage")
    List<Languages> findAllOrderById_language();
    //List<Languages> findAllOrderById_langueAndPaged(Integer id_langue, Pageable pageable);

    Optional<Languages> findByIso(String iso);

}

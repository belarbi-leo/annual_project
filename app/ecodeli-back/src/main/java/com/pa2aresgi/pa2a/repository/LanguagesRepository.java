package com.pa2aresgi.pa2a.repository;

import com.pa2aresgi.pa2a.modele.Languages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface LanguagesRepository extends JpaRepository<Languages, Integer>/*, PagingAndSortingRepository<Languages, Integer> */{
    @Query("select langue from Languages langue order by langue.id_langue")
    List<Languages> findAllOrderById_langue();
    //List<Languages> findAllOrderById_langueAndPaged(Integer id_langue, Pageable pageable);
}

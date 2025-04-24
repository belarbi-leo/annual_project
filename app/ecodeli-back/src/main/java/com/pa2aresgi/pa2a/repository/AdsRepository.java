package com.pa2aresgi.pa2a.repository;

import com.pa2aresgi.pa2a.modele.Ads;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdsRepository extends JpaRepository<Ads, Integer> {
    @Query("select ad from Ads ad order by ad.idAd")
    List<Ads> findAllOrderById_ad();
}

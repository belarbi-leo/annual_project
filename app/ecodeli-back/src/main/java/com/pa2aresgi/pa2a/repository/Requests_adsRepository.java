package com.pa2aresgi.pa2a.repository;

import com.pa2aresgi.pa2a.modele.Requests_ads;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Requests_adsRepository extends JpaRepository<Requests_ads,Integer> {
    @Query("select req_ad from Requests_ads req_ad order by req_ad.id_req_annonce")
    List<Requests_ads> findAllOrderById_req_annonce();
}

package ecodeli.repository;

import ecodeli.modele.RequestsAds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RequestsAdsRepository extends JpaRepository<RequestsAds,Integer> {
    @Query("select reqAd from RequestsAds reqAd order by reqAd.idReqAd")
    List<RequestsAds> findAllOrderById_req_ad();
}

package ecodeli.service;

import ecodeli.modele.RequestsAds;
import ecodeli.repository.RequestsAdsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RequestsAdsServiceImpl implements RequestsAdsService {
    private RequestsAdsRepository requestsAdsRepository;

    @Override
    public RequestsAds create(RequestsAds requestAd) {
        return requestsAdsRepository.save(requestAd);
    }

    @Override
    public List<RequestsAds> readAll() {
        return requestsAdsRepository.findAll();
    }

    @Override
    public List<RequestsAds> readAllOrderById() {
        return requestsAdsRepository.findAllOrderById_req_ad();
    }

    @Override
    public RequestsAds findById(Integer id) {
        if (requestsAdsRepository.findById(id).isPresent()){
            return requestsAdsRepository.findById(id).get();
        } else {
            throw new RuntimeException("requestAd not found ! ");
        }
    }

    @Override
    public RequestsAds update(Integer id, RequestsAds requestAd) {
        return requestsAdsRepository.findById(id).map(rqAd -> {
            rqAd.setUser(requestAd.getUser());
            rqAd.setAd(requestAd.getAd());
            rqAd.setStatusReqAd(requestAd.getStatusReqAd());
            rqAd.setDateCreationReqAd(requestAd.getDateCreationReqAd());
            rqAd.setDateAcceptReqAd(requestAd.getDateAcceptReqAd());
            rqAd.setDateStartReqAd(requestAd.getDateStartReqAd());
            rqAd.setLocationStartReqAd(requestAd.getLocationStartReqAd());
            rqAd.setSuiteStartReqAd(requestAd.getSuiteStartReqAd());
            rqAd.setLocalityStartReqAd(requestAd.getLocalityStartReqAd());
            rqAd.setStateStartReqAd(requestAd.getStateStartReqAd());
            //rqAd.setStreet_start_req_ad(requestAd.getStreet_start_req_ad());
            rqAd.setPostalCodeStartReqAd(requestAd.getPostalCodeStartReqAd());
            rqAd.setCountryStartReqAd(requestAd.getCountryStartReqAd());
            rqAd.setLatitudeStartReqAd(requestAd.getLatitudeStartReqAd());
            rqAd.setLongitudeStartReqAd(requestAd.getLongitudeStartReqAd());
            rqAd.setDateEndReqAd(requestAd.getDateEndReqAd());
            //rqAd.setStreet_end_req_ad(requestAd.getStreet_end_req_ad());
            rqAd.setLocationEndReqAd(requestAd.getLocationEndReqAd());
            rqAd.setSuiteEndReqAd(requestAd.getSuiteEndReqAd());
            rqAd.setLocalityEndReqAd(requestAd.getLocalityEndReqAd());
            rqAd.setStateEndReqAd(requestAd.getStateEndReqAd());
            rqAd.setPostalCodeEndReqAd(requestAd.getPostalCodeEndReqAd());
            rqAd.setCountryEndReqAd(requestAd.getCountryEndReqAd());
            rqAd.setLatitudeEndReqAd(requestAd.getLatitudeEndReqAd());
            rqAd.setLongitudeEndReqAd(requestAd.getLongitudeEndReqAd());
            rqAd.setMessageReqAd(requestAd.getMessageReqAd());
            rqAd.setPriceReqAd(requestAd.getPriceReqAd());
            return requestsAdsRepository.save(rqAd);
        }).orElseThrow(() -> new RuntimeException("requestAd not found !"));
    }

    @Override
    public String deleteById(Integer id) {
        requestsAdsRepository.deleteById(id);
        return "requestAd deleted !";
    }
}
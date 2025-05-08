package ecodeli.modelMapper.subClassImpl;

import ecodeli.DTO.create.RequestsAdsDTOCreate;
import ecodeli.DTO.read.RequestsAdsDTORead;
import ecodeli.modelMapper.RequestsAdsMapper;
import ecodeli.modele.Ads;
import ecodeli.modele.RequestsAds;
import ecodeli.modele.Users;
import ecodeli.repository.AdsRepository;
import ecodeli.repository.UsersRepository;
import ecodeli.tools.ResponseErrorV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequestsAdsMapperSubClassImpl {
    private final RequestsAdsMapper requestsAdsMapper;
    private final UsersRepository usersRepository;
    private final AdsRepository adsRepository;

    public RequestsAdsDTORead toDtoRead(RequestsAds requestAd){
        return requestsAdsMapper.toDtoRead(requestAd);
    }

    public Object fromDtoCreate(RequestsAdsDTOCreate requestAdDtoCreate){
        RequestsAds requestAd = requestsAdsMapper.fromDtoCreate(requestAdDtoCreate);
        Optional<Users> user = usersRepository.findById(requestAdDtoCreate.getUser());
        Optional<Ads> ad = adsRepository.findById(requestAdDtoCreate.getAd());
        ResponseErrorV2 retourNeg = new ResponseErrorV2();
        if (user.isEmpty()) retourNeg.add("user", "Id user not found !");
        else requestAd.setUser(user.get());
        if (ad.isEmpty()) retourNeg.add("ad", "Id ad not found !");
        else requestAd.setAd(ad.get());
        if (!retourNeg.getErrors().isEmpty())
            return retourNeg;
        else
            return requestAd;
    }

    public Object updateRequestAdFromDtoCreate(RequestsAdsDTOCreate requestAdDtoCreate, RequestsAds requestAd){
        RequestsAds rqAd = requestsAdsMapper.updateRequestAdFromDtoCreate(requestAdDtoCreate, requestAd);
        ResponseErrorV2 retourNeg = new ResponseErrorV2();
        if (requestAdDtoCreate.getUser() != null){
            Optional<Users> user = usersRepository.findById(requestAdDtoCreate.getUser());
            if (user.isEmpty()) retourNeg.add("user", "Id user not found !");
            else rqAd.setUser(user.get());
        }
        if (requestAdDtoCreate.getAd() != null){
            Optional<Ads> ad = adsRepository.findById(requestAdDtoCreate.getAd());
            if (ad.isEmpty()) retourNeg.add("ad", "Id ad not found !");
            else rqAd.setAd(ad.get());
        }
        if (!retourNeg.getErrors().isEmpty())
            return retourNeg;
        else
            return rqAd;
    }
}

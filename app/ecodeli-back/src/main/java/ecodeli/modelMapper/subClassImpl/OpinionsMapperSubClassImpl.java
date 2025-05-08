package ecodeli.modelMapper.subClassImpl;

import ecodeli.DTO.create.OpinionsDTOCreate;
import ecodeli.DTO.read.OpinionsDTORead;
import ecodeli.modelMapper.OpinionsMapper;
import ecodeli.modele.Ads;
import ecodeli.modele.Opinions;
import ecodeli.repository.AdsRepository;
import ecodeli.tools.ResponseErrorV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OpinionsMapperSubClassImpl {
    private final OpinionsMapper opinionsMapper;
    private final AdsRepository adsRepository;

    public OpinionsDTORead toDtoRead(Opinions opinion){
        return opinionsMapper.toDtoRead(opinion);
    }

    public Object fromDtoCreate(OpinionsDTOCreate opinionDtoCreate){
        Opinions opinion = opinionsMapper.fromDtoCreate(opinionDtoCreate);
        Optional<Ads> ad = adsRepository.findById(opinionDtoCreate.getAd());
        ResponseErrorV2 retourNeg = new ResponseErrorV2();
        if (ad.isEmpty()) retourNeg.add("ad", "Id ad not found !");
        else opinion.setAd(ad.get());
        if (!retourNeg.getErrors().isEmpty()){
            return retourNeg;
        }
        else{
            return opinion;
        }
    }

    public Object updateOpinionFromDtoCreate(OpinionsDTOCreate opinionDtoCreate, Opinions opinion){
        Opinions op = opinionsMapper.updateOpinionFromDtoCreate(opinionDtoCreate, opinion);
        ResponseErrorV2 retourNeg = new ResponseErrorV2();
        if (opinionDtoCreate.getAd() != null){
            Optional<Ads> ad = adsRepository.findById(opinionDtoCreate.getAd());
            if (ad.isEmpty()) retourNeg.add("ad", "Id ad not found !");
            else op.setAd(ad.get());
        }
        if (!retourNeg.getErrors().isEmpty())
            return retourNeg;
        else
            return op;
    }
}

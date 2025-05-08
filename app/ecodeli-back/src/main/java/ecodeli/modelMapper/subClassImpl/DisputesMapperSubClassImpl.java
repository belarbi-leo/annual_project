package ecodeli.modelMapper.subClassImpl;

import ecodeli.DTO.create.DisputesDTOCreate;
import ecodeli.DTO.read.DisputesDTORead;
import ecodeli.modelMapper.DisputesMapper;
import ecodeli.modele.Ads;
import ecodeli.modele.Disputes;
import ecodeli.modele.Users;
import ecodeli.repository.AdsRepository;
import ecodeli.repository.UsersRepository;
import ecodeli.tools.ResponseErrorV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DisputesMapperSubClassImpl {
    private final DisputesMapper disputesMapper;
    private final AdsRepository adsRepository;
    private final UsersRepository usersRepository;

    public DisputesDTORead toDtoRead(Disputes dispute){
        return disputesMapper.toDtoRead(dispute);
    }

    public Object fromDtoCreate(DisputesDTOCreate disputeDtoCreate){
        Disputes dispute = disputesMapper.fromDtoCreate(disputeDtoCreate);
        Optional<Ads> ad = adsRepository.findById(disputeDtoCreate.getAd());
        Optional<Users> user = usersRepository.findById(disputeDtoCreate.getUser());
        ResponseErrorV2 retourNeg = new ResponseErrorV2();
        if (ad.isEmpty()) retourNeg.add("ad", " Id ad not found !");
        else dispute.setAd(ad.get());
        if (user.isEmpty()) retourNeg.add("user", " Id user not found !");
        else dispute.setUser(user.get());
        if (!retourNeg.getErrors().isEmpty())
            return retourNeg;
        else
            return dispute;
    }

    public Object updateDisputeFromDtoCreate(DisputesDTOCreate disputeDtoCreate, Disputes dispute){
        Disputes dpt = disputesMapper.updateDisputeFromDtoCreate(disputeDtoCreate, dispute);
        ResponseErrorV2 retourNeg = new ResponseErrorV2();
        if (disputeDtoCreate.getAd() != null){
            Optional<Ads> ad = adsRepository.findById(disputeDtoCreate.getAd());
            if (ad.isEmpty()) retourNeg.add("ad", " Id ad not found !");
            else dpt.setAd(ad.get());
        }
        if (disputeDtoCreate.getUser() != null){
            Optional<Users> user = usersRepository.findById(disputeDtoCreate.getUser());
            if (user.isEmpty()) retourNeg.add("user", " Id user not found !");
            else dpt.setUser(user.get());
        }
        if (!retourNeg.getErrors().isEmpty())
            return retourNeg;
        else
            return dpt;
    }
}

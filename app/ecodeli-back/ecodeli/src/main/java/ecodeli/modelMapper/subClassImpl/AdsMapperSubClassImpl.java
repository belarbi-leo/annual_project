package ecodeli.modelMapper.subClassImpl;

import ecodeli.DTO.create.AdsDTOCreate;
import ecodeli.DTO.read.AdsDTORead;
import ecodeli.modelMapper.AdsMapper;
import ecodeli.modele.Ads;
import ecodeli.modele.Services;
import ecodeli.modele.Users;
import ecodeli.repository.ServicesRepository;
import ecodeli.repository.UsersRepository;
import ecodeli.tools.ResponseErrorV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdsMapperSubClassImpl {
    private final AdsMapper adsMapper;
    private final UsersRepository usersRepository;
    private final ServicesRepository servicesRepository;

    public AdsDTORead toDtoRead(Ads ad) {
        return adsMapper.toDtoRead(ad);
    }

    public Object fromDtoCreate(AdsDTOCreate adDtoCreate){
        Ads ad = adsMapper.fromDtoCreate(adDtoCreate);
        Optional<Users> userCreator = usersRepository.findById(adDtoCreate.getUserCreator());
        Optional<Users> userAccept = usersRepository.findById(adDtoCreate.getUserAccept());
        Optional<Services> service = servicesRepository.findById(adDtoCreate.getSvc());
        ResponseErrorV2 retourNeg = new ResponseErrorV2();
        if (userCreator.isEmpty()) retourNeg.add("userCreator", "Id for the user creating the request not found !");
        else ad.setUserCreator(userCreator.get());
        if (userAccept.isEmpty()) retourNeg.add("userAccept", "Id for the user accepting the request not found !");
        else ad.setUserAccept(userAccept.get());
        if(service.isEmpty()) retourNeg.add("service", "Id service not found !");
        else ad.setSvc(service.get());
        if (!retourNeg.getErrors().isEmpty())
            return retourNeg;
        else
            return ad;
    }

    public Object updateAdFromDtoCreate(AdsDTOCreate adDtoCreate, Ads ad){
        Ads ad2 = adsMapper.updateAdFromDtoCreate(adDtoCreate, ad);
        ResponseErrorV2 retourNeg = new ResponseErrorV2();
        if (adDtoCreate.getUserCreator() !=  null){
            Optional<Users> userCreator = usersRepository.findById(adDtoCreate.getUserCreator());
            if (userCreator.isEmpty()) retourNeg.add("userCreator", "Id for the user creating the request not found !");
            else ad2.setUserCreator(userCreator.get());
        }
        if (adDtoCreate.getUserAccept() !=  null){
            Optional<Users> userAccept = usersRepository.findById(adDtoCreate.getUserAccept());
            if (userAccept.isEmpty()) retourNeg.add("userAccept", "Id for the user accepting the request not found !");
            else ad2.setUserAccept(userAccept.get());
        }
        if (adDtoCreate.getSvc() !=  null){
            Optional<Services> service = servicesRepository.findById(adDtoCreate.getSvc());
            if (service.isEmpty()) retourNeg.add("service", "Id service not found !");
            else ad2.setSvc(service.get());
        }
        if (!retourNeg.getErrors().isEmpty())
            return retourNeg;
        else
            return ad2;
    }
}

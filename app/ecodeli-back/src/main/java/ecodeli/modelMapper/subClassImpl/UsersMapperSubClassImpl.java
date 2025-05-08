package ecodeli.modelMapper.subClassImpl;

import ecodeli.DTO.create.UsersDTOCreate;
import ecodeli.DTO.read.UsersDTORead;
import ecodeli.modelMapper.UsersMapper;
import ecodeli.modele.*;
import ecodeli.repository.LanguagesRepository;
import ecodeli.repository.SubscriptionsRepository;
import ecodeli.tools.PasswordHasher;
import ecodeli.tools.ResponseErrorV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersMapperSubClassImpl {

    private final UsersMapper usersMapper;
    private final LanguagesRepository languagesRepository;
    private final SubscriptionsRepository subscriptionsRepository;

    public UsersDTORead toDtoRead(Users user){
        return usersMapper.toDtoRead(user);
    }

    public Object fromDtoCreate(UsersDTOCreate userDtoCreate){
        Users user = usersMapper.fromDtoCreate(userDtoCreate);
        //Optional<Languages> language = languagesRepository.findById(userDtoCreate.getLanguage());
        Optional<Languages> language = languagesRepository.findByIso(userDtoCreate.getLanguage().toUpperCase());
        if (userDtoCreate.getSubscription() == null) userDtoCreate.setSubscription(1);    //valeur par défaut implémentée ici car je ne sais pas trop comment faire autrement niveau mapping
        Optional<Subscriptions > subscription = subscriptionsRepository.findById(userDtoCreate.getSubscription());
        ResponseErrorV2 retourNeg = new ResponseErrorV2();
        if (language.isEmpty()) retourNeg.add("'language","Iso for the language not found !");
        else user.setLanguage(language.get());
        if (subscription.isEmpty()) retourNeg.add("subscription","Id subscription not found !");
        else user.setSubscription(subscription.get());
        if (!retourNeg.getErrors().isEmpty())
            return retourNeg;
        else {
            user.setPassword(PasswordHasher.hashPassword(user.getPassword()));
            return user;
        }
    }

    /*public Users updateUserFromDtoCreate(UsersDTOCreate userDtoCreate, Users user){
        return usersMapper.updateUserFromDtoCreate(userDtoCreate, user);
    }*/

    public Object updateUserFromDtoCreate(UsersDTOCreate userDtoCreate, Users user){
        Users usr = usersMapper.updateUserFromDtoCreate(userDtoCreate, user);
        ResponseErrorV2 retourNeg = new ResponseErrorV2();
        if (userDtoCreate.getLanguage() != null) {
            //Optional<Languages> language = languagesRepository.findById(userDtoCreate.getLanguage());
            Optional<Languages> language = languagesRepository.findByIso(userDtoCreate.getLanguage().toUpperCase());
            if (language.isEmpty()) retourNeg.add("'language","Iso for the language not found !");
            else usr.setLanguage(language.get());
        }
        if(userDtoCreate.getSubscription() != null){
            Optional<Subscriptions > subscription = subscriptionsRepository.findById(userDtoCreate.getSubscription());
            if (subscription.isEmpty()) retourNeg.add("subscription","Id subscription not found !");
            else usr.setSubscription(subscription.get());
        }
        if (!retourNeg.getErrors().isEmpty())
            return retourNeg;
        else {
            if (usr.getPassword() != null)
                usr.setPassword(PasswordHasher.hashPassword(usr.getPassword()));
            return usr;
        }
        //return usersMapper.updateUserFromDtoCreate(userDtoCreate, user);
    }
}

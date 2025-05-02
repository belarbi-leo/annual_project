package ecodeli.service;


import ecodeli.enumeratation.RoleEnum;
import ecodeli.modele.*;
import ecodeli.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {
    private UsersRepository usersRepository;

    @Override
    public Users create(Users user) {
        return usersRepository.save(user);
    }

    @Override
    public List<Users> readAll() {
        return usersRepository.findAll();
    }

    @Override
    public List<Users> readAllOrderById() {
        return usersRepository.findAllOrderById_user();
    }

    @Override
    public List<Users> readAllByRole(RoleEnum role){
        return usersRepository.findAllByRole(role);
    }

    @Override
    public List<Users> readAllByRoleOrderById(RoleEnum role){
        return usersRepository.findAllByRoleOrderById_user(role);
    }

    @Override
    public Users findById(Integer id) {
        if (usersRepository.findById(id).isPresent()){
            return usersRepository.findById(id).get();
        } else {
            throw new RuntimeException("User not found ! ");
        }
    }

    @Override
    public Users update(Integer id, Users user) {
        return usersRepository.findById(id).map(usr -> {
            usr.setDateRegistration(user.getDateRegistration());
            usr.setRole(user.getRole());
            usr.setAccountStatus(user.getAccountStatus());
            usr.setDateStatus(user.getDateStatus());
            usr.setEmail(user.getEmail());
            usr.setPassword(user.getPassword());
            usr.setPhoneNumber(user.getPhoneNumber());
            usr.setFirstName(user.getFirstName());
            usr.setLastName(user.getLastName());
            usr.setCompanyName(user.getCompanyName());
            usr.setSiret(user.getSiret());
            usr.setPhotoUser(user.getPhotoUser());
            usr.setBio(user.getBio());
            usr.setLocation(user.getLocation());
            usr.setSuite(user.getSuite());
            usr.setLocality(user.getLocality());
            usr.setState(user.getState());
            //usr.setStreet(user.getStreet());
            usr.setPostalCode(user.getPostalCode());
            usr.setCountry(user.getCountry());
            usr.setLatitude(user.getLatitude());
            usr.setLongitude(user.getLongitude());
            /*
            usr.setCode_payment(user.getCode_payment());
            usr.setExpiration_payment(user.getExpiration_payment());
            usr.setIban(user.getIban());*/
            usr.setSubscription(user.getSubscription());
            usr.setLanguage(user.getLanguage());/*
            usr.setDipustes_list(user.getDipustes_list());
            usr.setAuthorizations_list(user.getAuthorizations_list());
            usr.setServices_list(user.getServices_list());
            usr.setRoutes_list(user.getRoutes_list());
            usr.setRequests_svc_user_req_list(user.getRequests_svc_user_req_list());
            usr.setRequests_svc_admin_res_list(user.getRequests_svc_admin_res_list());
            usr.setAds_user_creator_list(user.getAds_user_creator_list());
            usr.setAds_user_accept_list(user.getAds_user_accept_list());*/
            return usersRepository.save(usr);
        }).orElseThrow(() -> new RuntimeException("User not found !"));
    }

    @Override
    public String deleteById(Integer id) {
        usersRepository.deleteById(id);
        return "User deleted !";
    }
}
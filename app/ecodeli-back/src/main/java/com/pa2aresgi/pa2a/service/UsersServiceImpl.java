package com.pa2aresgi.pa2a.service;


import com.pa2aresgi.pa2a.enumeratation.Role_enum;
import com.pa2aresgi.pa2a.modele.*;
import com.pa2aresgi.pa2a.repository.UsersRepository;
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
    public List<Users> readAllByRole(Role_enum role){
        return usersRepository.findAllByRole(role);
    }

    @Override
    public List<Users> readAllByRoleOrderById(Role_enum role){
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
            usr.setDate_registration(user.getDate_registration());
            usr.setRole(user.getRole());
            usr.setAccount_status(user.getAccount_status());
            usr.setDate_status(user.getDate_status());
            usr.setEmail(user.getEmail());
            usr.setPassword(user.getPassword());
            usr.setPhone_number(user.getPhone_number());
            usr.setFirst_name(user.getFirst_name());
            usr.setLast_name(user.getLast_name());
            usr.setCompany_name(user.getCompany_name());
            usr.setSiret(user.getSiret());
            usr.setPhoto_user(user.getPhoto_user());
            usr.setBio(user.getBio());
            usr.setLocation(user.getLocation());
            usr.setSuite(user.getSuite());
            usr.setLocality(user.getLocality());
            usr.setState(user.getState());
            //usr.setStreet(user.getStreet());
            usr.setPostal_code(user.getPostal_code());
            usr.setCountry(user.getCountry());
            /*
            usr.setCode_payment(user.getCode_payment());
            usr.setExpiration_payment(user.getExpiration_payment());
            usr.setIban(user.getIban());*/
            usr.setId_subscription(user.getId_subscription());
            usr.setId_language(user.getId_language());/*
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
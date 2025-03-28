package com.pa2aresgi.pa2a.service;


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
            usr.setFirst_name(user.getFirst_name());
            usr.setLast_name(user.getLast_name());
            usr.setCompany_name(user.getCompany_name());
            usr.setPhoto_user(user.getPhoto_user());
            usr.setBio(user.getBio());
            usr.setSiret(user.getSiret());
            usr.setStreet(user.getStreet());
            usr.setPostal_code(user.getPostal_code());
            usr.setCountry(user.getCountry());
            usr.setCode_payment(user.getCode_payment());
            usr.setExpiration_payment(user.getExpiration_payment());
            usr.setIban(user.getIban());
            usr.setId_sub(user.getId_sub());
            usr.setId_langue(user.getId_langue());/*
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
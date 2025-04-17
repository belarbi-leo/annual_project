package com.pa2aresgi.pa2a.service;


import com.pa2aresgi.pa2a.DTO.create.UsersDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.UsersDTORead;
import com.pa2aresgi.pa2a.enumeratation.Role_enum;
import com.pa2aresgi.pa2a.modelMapper.UsersMapper;
import com.pa2aresgi.pa2a.modele.Users;
import com.pa2aresgi.pa2a.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UsersServiceDTOImpl implements UsersServiceDTO {
    private UsersRepository usersRepository;
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public UsersDTORead create(UsersDTOCreate userDtoCreate) {
        //Users user = toEntity(userDtoCreate);
        //UsersMapper mapper = null;
        Users user = usersMapper.fromDtoCreate(userDtoCreate);
        return usersMapper.toDtoRead(usersRepository.save(user));
        //return usersRepository.save(userDtoCreate);
    }

    @Override
    public List<UsersDTORead> readAll() {
        //List<UsersDTORead> usersDTOReads = List.of();
        List<UsersDTORead> usersDTOReads = new ArrayList<UsersDTORead>();
        List<Users> users = usersRepository.findAll();
        //UsersMapper mapper = null;
        for (Users user : users) {
            usersDTOReads.add(usersMapper.toDtoRead(user));
        }
        return usersDTOReads;
        //return usersRepository.findAll();
    }

    @Override
    public List<UsersDTORead> readAllOrderById() {
        //List<UsersDTORead> usersDTOReads = List.of();
        List<UsersDTORead> usersDTOReads = new ArrayList<UsersDTORead>();
        List<Users> users = usersRepository.findAllOrderById_user();
        //UsersMapper mapper = null;
        for (Users user : users) {
            usersDTOReads.add(usersMapper.toDtoRead(user));
        }
        return usersDTOReads;
        //return usersRepository.findAllOrderById_user();
    }

    @Override
    public List<UsersDTORead> readAllByRole(Role_enum role){
        List<UsersDTORead> usersDTOReads = new ArrayList<UsersDTORead>();
        List<Users> users = usersRepository.findAllByRole(role);
        //UsersMapper mapper = null;
        for (Users user : users) {
            usersDTOReads.add(usersMapper.toDtoRead(user));
        }
        return usersDTOReads;
        //return usersRepository.findAllByRole(role);
    }

    @Override
    public List<UsersDTORead> readAllByRoleOrderById(Role_enum role){
        //List<UsersDTORead> usersDTOReads = List.of();
        List<UsersDTORead> usersDTOReads = new ArrayList<UsersDTORead>();
        List<Users> users = usersRepository.findAllByRoleOrderById_user(role);
        //UsersMapper mapper= null;
        for (Users user : users) {
            usersDTOReads.add(usersMapper.toDtoRead(user));
        }
        return usersDTOReads;
        //return usersRepository.findAllByRoleOrderById_user(role);
    }

    @Override
    public UsersDTORead findById(Integer id) {
        if (usersRepository.findById(id).isPresent()){
            //UsersMapper mapper = null;
            return usersMapper.toDtoRead(usersRepository.findById(id).get());
        } else {
            throw new RuntimeException("User not found ! ");
        }
    }

    @Override
    public UsersDTORead update(Integer id, UsersDTOCreate userDtoCreate) {
        //UsersMapper mapper = null;
        Users user = usersMapper.fromDtoCreate(userDtoCreate);
        return usersRepository.findById(id).map(usr -> {
            if(user.getDate_registration() != null) usr.setDate_registration(user.getDate_registration());
            if(user.getDate_accept_cgu() != null) usr.setDate_accept_cgu(user.getDate_accept_cgu());
            if(user.getDate_accept_cgv() != null) usr.setDate_accept_cgv(user.getDate_accept_cgv());
            if(user.getRole() != null) usr.setRole(user.getRole());
            if(user.getAccount_status() != null) usr.setAccount_status(user.getAccount_status());
            if(user.getDate_status() != null) usr.setDate_status(user.getDate_status());
            if(user.getEmail() != null) usr.setEmail(user.getEmail());
            if(user.getPassword() != null) usr.setPassword(user.getPassword());
            if(user.getPhone_number() != null) usr.setPhone_number(user.getPhone_number());
            if(user.getFirst_name() != null) usr.setFirst_name(user.getFirst_name());
            if(user.getLast_name() != null) usr.setLast_name(user.getLast_name());
            if(user.getCompany_name() != null) usr.setCompany_name(user.getCompany_name());
            if(user.getSiret() != null) usr.setSiret(user.getSiret());
            if(user.getPhoto_user() != null) usr.setPhoto_user(user.getPhoto_user());
            if(user.getBio() != null) usr.setBio(user.getBio());
            //if(user.getStreet() != null) usr.setStreet(user.getStreet());
            if(user.getLocation() != null) usr.setLocation(user.getLocation());
            if(user.getSuite() != null) usr.setSuite(user.getSuite());
            if(user.getLocality() != null) usr.setLocality(user.getLocality());
            if(user.getState() != null) usr.setState(user.getState());
            if(user.getPostal_code() != null) usr.setPostal_code(user.getPostal_code());
            if(user.getCountry() != null) usr.setCountry(user.getCountry());
            if(user.getId_subscription() != null) usr.setId_subscription(user.getId_subscription());
            if(user.getId_language() != null) usr.setId_language(user.getId_language());
            return usersMapper.toDtoRead(usersRepository.save(usr));
        }).orElseThrow(() -> new RuntimeException("User not found !"));
    }

    @Override
    public String deleteById(Integer id) {
        usersRepository.deleteById(id);
        return "User deleted !";
    }

}
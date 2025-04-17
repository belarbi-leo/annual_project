package com.pa2aresgi.pa2a.DTO.read;

import com.pa2aresgi.pa2a.enumeratation.Account_status_enum;
import com.pa2aresgi.pa2a.enumeratation.Role_enum;
import com.pa2aresgi.pa2a.modele.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class UsersDTORead {
    private Integer id_user;
    private Timestamp date_registration;
    private Timestamp date_accept_cgu;
    private Timestamp date_accept_cgv;
    private Role_enum role;
    private Account_status_enum account_status;
    private Timestamp date_status;
    private String email;
    //private String password;
    private Integer phone_number;
    private String first_name;
    private String last_name;
    private String company_name;
    private Long siret;
    private String photo_user;
    private String bio;
    private String location;
    private String suite;
    private String locality;
    private String state;
    private String postal_code;
    private String country;
    private Subscriptions id_subscription;
    private Languages id_langue;
}

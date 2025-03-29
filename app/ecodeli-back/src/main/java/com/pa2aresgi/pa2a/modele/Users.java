package com.pa2aresgi.pa2a.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pa2aresgi.pa2a.enumeratation.Account_status_enum;
import com.pa2aresgi.pa2a.enumeratation.Role_enum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_user;
    @Column
    private Timestamp date_registration;
    @Column
    @Enumerated(EnumType.STRING)
    private Role_enum role;
    @Column
    @Enumerated(EnumType.STRING)
    private Account_status_enum account_status;
    @Column
    private Timestamp date_status;
    @Column
    private String email;
    @Column(length=255)
    private String password;
    @Column(length=50)
    private String first_name;
    @Column(length=50)
    private String last_name;
    @Column(length=100)
    private String company_name;
    @Column
    private String photo_user;
    @Column(length=255)
    private String bio;
    @Column
    private long siret;
    @Column(length=255)
    private String street;
    @Column(length=20)
    private String postal_code;
    @Column(length=100)
    private String country;
    @Column
    private String code_payment;
    @Column(length=7)
    private String expiration_payment;
    @Column(length=34)
    private String iban;
    @ManyToOne
    @JoinColumn(name="id_subscription", nullable = false)
    private Subscriptions id_subscription;
    @ManyToOne
    @JoinColumn(name="id_langue", nullable = false)
    private Languages id_langue;
    @OneToMany(mappedBy = "id_user")
    @JsonIgnore
    private List<Disputes> dipustes_list = new ArrayList<>();
    @OneToMany(mappedBy = "id_user")
    @JsonIgnore
    private List<Authorizations> authorizations_list = new ArrayList<>();
    @OneToMany(mappedBy = "id_admin_creator")
    @JsonIgnore
    private List<Services> services_list = new ArrayList<>();
    @OneToMany(mappedBy = "id_user")
    @JsonIgnore
    private List<Routes> routes_list = new ArrayList<>();
    @OneToMany(mappedBy = "id_user_req")
    @JsonIgnore
    private List<Requests_svc> requests_svc_user_req_list = new ArrayList<>();
    @OneToMany(mappedBy = "id_admin_res")
    @JsonIgnore
    private List<Requests_svc> requests_svc_admin_res_list = new ArrayList<>();
    @OneToMany(mappedBy = "id_user_creator")
    @JsonIgnore
    private List<Ads> ads_user_creator_list = new ArrayList<>();
    @OneToMany(mappedBy = "id_user_accept")
    @JsonIgnore
    private List<Ads> ads_user_accept_list = new ArrayList<>();
}

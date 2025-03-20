package com.pa2aresgi.pa2a.modele;

import com.pa2aresgi.pa2a.enumeratation.Account_status_enum;
import com.pa2aresgi.pa2a.enumeratation.Role_enum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_user;
    private Timestamp date_create;
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
    private Integer code_payment;
    @Column(length=7)
    private String expiration_payment;
    @Column(length=34)
    private String iban;
    @OneToOne
    @JoinColumn(name="id_sub", nullable = false)
    private Subscriptions id_subscription;
    @OneToOne
    @JoinColumn(name="id_langue", nullable = false)
    private Languages id_langue;
}

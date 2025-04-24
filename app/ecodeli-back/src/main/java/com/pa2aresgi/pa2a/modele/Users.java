package com.pa2aresgi.pa2a.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pa2aresgi.pa2a.enumeratation.AccountStatusEnum;
import com.pa2aresgi.pa2a.enumeratation.RoleEnum;
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
    @Column(name="id_user")
    private Integer idUser;
    @Column(name="date_registration")
    private Timestamp dateRegistration;
    @Column(name="date_accept_cgu")
    private Timestamp dateAcceptCgu;
    @Column(name="date_accept_cgv")
    private Timestamp dateAcceptCgv;
    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;
    @Column(name="account_status")
    @Enumerated(EnumType.STRING)
    private AccountStatusEnum accountStatus;
    @Column(name="date_status")
    private Timestamp dateStatus;
    @Column(name="email",length=255)
    private String email;
    @Column(name="password", length=255)
    private String password;
    @Column(name="phone_number", length=10)
    private String phoneNumber;
    @Column(name="first_name", length=50)
    private String firstName;
    @Column(name="last_name", length=50)
    private String lastName;
    @Column(name="company_name", length=100)
    private String companyName;
    @Column(name="siret", length=14)
    private String siret;
    @Column(name="photo_user", columnDefinition="text")
    private String photoUser;
    @Column(name="bio", length=255)
    private String bio;
    /*@Column(length=255)
    private String street;*/
    @Column(name="location", length=255)
    private String location;
    @Column(name="suite", length = 255)
    private String suite;
    @Column(name="locality", length=255)
    private String locality;
    @Column(name="state", length = 255)
    private String state;
    @Column(name="postal_code", length=20)
    private String postalCode;
    @Column(name="country", length=100)
    private String country;
    @Column(name="latitude", columnDefinition = "decimal(9,6)")
    private Float latitude;
    @Column(name="longitude", columnDefinition = "decimal(9,6)")
    private Float longitude;
    /*
    @Column
    private String code_payment;
    @Column(length=7)
    private String expiration_payment;
    @Column(length=34)
    private String iban;*/
    @ManyToOne
    @JoinColumn(name="id_subscription", nullable = false)
    private Subscriptions subscription;
    @ManyToOne
    @JoinColumn(name="id_language", nullable = false)
    private Languages language;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Disputes> dipustesList = new ArrayList<>();
    /*
    @OneToMany(mappedBy = "id_user")
    @JsonIgnore
    private List<Authorizations> authorizations_list = new ArrayList<>();*/
    @OneToMany(mappedBy = "adminCreator")
    @JsonIgnore
    private List<Services> servicesList = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Routes> routesList = new ArrayList<>();
    @OneToMany(mappedBy = "userReq")
    @JsonIgnore
    private List<RequestsServices> requestsServicesUserReqList = new ArrayList<>();
    @OneToMany(mappedBy = "adminRes")
    @JsonIgnore
    private List<RequestsServices> requestsServicesAdminResList = new ArrayList<>();
    @OneToMany(mappedBy = "userCreator")
    @JsonIgnore
    private List<Ads> adsUserCreatorList = new ArrayList<>();
    @OneToMany(mappedBy = "userAccept")
    @JsonIgnore
    private List<Ads> adsUserAcceptList = new ArrayList<>();

    public void afficher() {
        System.out.println("===== USERS ENTITY =====");
        System.out.println("idUser: " + idUser);
        System.out.println("dateRegistration: " + dateRegistration);
        System.out.println("dateAcceptCgu: " + dateAcceptCgu);
        System.out.println("dateAcceptCgv: " + dateAcceptCgv);
        System.out.println("role: " + role);
        System.out.println("accountStatus: " + accountStatus);
        System.out.println("dateStatus: " + dateStatus);
        System.out.println("email: " + email);
        System.out.println("password: " + password);
        System.out.println("phoneNumber: " + phoneNumber);
        System.out.println("firstName: " + firstName);
        System.out.println("lastName: " + lastName);
        System.out.println("companyName: " + companyName);
        System.out.println("siret: " + siret);
        System.out.println("photoUser: " + photoUser);
        System.out.println("bio: " + bio);
        System.out.println("location: " + location);
        System.out.println("suite: " + suite);
        System.out.println("locality: " + locality);
        System.out.println("state: " + state);
        System.out.println("postalCode: " + postalCode);
        System.out.println("country: " + country);
        System.out.println("subscription: " + (subscription != null ? subscription.getIdSubscription() : null));
        System.out.println("language: " + (language != null ? language.getIdLanguage() : null));
    }


}

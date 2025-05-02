package ecodeli.DTO.read;

import ecodeli.enumeratation.AccountStatusEnum;
import ecodeli.enumeratation.RoleEnum;
import ecodeli.modele.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class UsersDTORead {
    private Integer idUser;
    private Timestamp dateRegistration;
    private Timestamp dateAcceptCgu;
    private Timestamp dateAcceptCgv;
    private RoleEnum role;
    private AccountStatusEnum accountStatus;
    private Timestamp dateStatus;
    private String email;
    //private String password;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String companyName;
    private String siret;
    private String photoUser;
    private String bio;
    private String location;
    private String suite;
    private String locality;
    private String state;
    private String postalCode;
    private String country;
    private Float latitude;
    private Float longitude;
    private Subscriptions subscription;
    private Languages language;
}

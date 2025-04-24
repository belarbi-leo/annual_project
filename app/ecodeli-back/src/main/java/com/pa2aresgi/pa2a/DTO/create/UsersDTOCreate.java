package com.pa2aresgi.pa2a.DTO.create;

import com.pa2aresgi.pa2a.enumeratation.AccountStatusEnum;
import com.pa2aresgi.pa2a.enumeratation.RoleEnum;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class UsersDTOCreate {
    private Timestamp dateRegistration;
    private Timestamp dateAcceptCgu;
    private Timestamp dateAcceptCgv;
    private RoleEnum role;
    private AccountStatusEnum accountStatus;
    private Timestamp dateStatus;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    @Size(min = 12, max = 200,message = "The password must be composed by minimum 12 characters")
    @NotBlank(message = "Password must not be blank")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, and one number"
    )
    private String password;
    @Size(min = 10, max = 10, message = "The phone number must be 10 digits")
    private String phoneNumber;
    @Size(min = 3, max = 50, message = "The first name have to be between 3 and 50 characters")
    private String firstName;
    @Size(min = 3, max = 50, message = "The last name have to be between 3 and 50 characters")
    private String lastName;
    @Size(min = 1, max = 100, message = "The company name have to be between 1 and 100 characters")
    private String companyName;
    @Size(min = 14, max = 14, message = "The siret have to be 14 digits")
    private String siret;
    private String photoUser;
    @Size(max = 255, message = "Bio field must be under 255 characters")
    private String bio;
    //private String street;
    private String location;
    private String suite;
    private String locality;
    private String state;
    private String postalCode;
    private String country;
    private Float latitude;
    private Float longitude;
    private Integer subscription;
    /*@NotNull(message = "A language is needed")
    private Integer id_language;*/
    @NotBlank(message = "A language is needed")
    @Size(min = 2, max = 2, message = "The ISO for the language must be 2 characters")
    private String language;

    public void afficher() {
        System.out.println("===== USERS DTO CREATE =====");
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
        System.out.println("subscription: " + subscription);
        System.out.println("language: " + language);
    }

}

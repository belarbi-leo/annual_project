package ecodeli.DTO.create;

import ecodeli.enumeratation.AccountStatusEnum;
import ecodeli.enumeratation.RoleEnum;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class UsersDTOCreate {
    //DEFAULT now()
    private Timestamp dateRegistration;
    private Timestamp dateAcceptCgu;
    private Timestamp dateAcceptCgv;
    private RoleEnum role;
    //DEFAULT 'overlays'
    private AccountStatusEnum accountStatus;
    //DEFAULT now()
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
    //DEFAULT NULL
    @Size(min = 14, max = 14, message = "The siret have to be 14 digits")
    private String siret;
    private String photoUser;
    @Size(max = 255, message = "Bio field must be under 255 characters")
    private String bio;
    //private String street;
    @Size(max = 255, message = "Location must be less than 255 characters")
    private String location;
    @Size(max = 255, message = "Additional address must be less than 255 characters")
    private String suite;
    @Size(max = 255, message = "Locality must be less than 255 characters")
    private String locality;
    @Size(max = 255, message = "State must be less than 255 characters")
    private String state;
    @Size(max = 20, message = "Postal code must be less than 20 characters")
    private String postalCode;
    @Size(max = 100, message = "Country must be less than 100 characters")
    private String country;
    private Float latitude;
    private Float longitude;
    //DEFAULT 1 donc pas de NOT NULL
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

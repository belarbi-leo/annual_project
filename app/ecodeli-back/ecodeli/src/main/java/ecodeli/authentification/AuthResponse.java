package ecodeli.authentification;

import ecodeli.enumeratation.RoleEnum;
import lombok.Data;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;

@Profile("prod")
@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private RoleEnum role;
}

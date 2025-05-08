package ecodeli.authentification;

import lombok.Data;
import org.springframework.context.annotation.Profile;

@Profile("prod")
@Data
public class AuthRequest {
    private String email;
    private String password;
}

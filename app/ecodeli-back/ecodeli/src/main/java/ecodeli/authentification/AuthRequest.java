package ecodeli.authentification;

import lombok.Data;
import org.springframework.context.annotation.Profile;

@Profile("dev")
@Data
public class AuthRequest {
    private String email;
    private String password;
}

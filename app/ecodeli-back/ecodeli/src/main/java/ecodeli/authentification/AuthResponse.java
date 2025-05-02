package ecodeli.authentification;

import lombok.Data;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;

@Profile("dev")
@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
}

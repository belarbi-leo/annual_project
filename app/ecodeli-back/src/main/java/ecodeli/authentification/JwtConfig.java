package ecodeli.authentification;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("prod")
@Configuration
public class JwtConfig {

    @Value("${jwt.secretKey}")
    private String secretKey;

    public String getSecretKey() {
        return secretKey;
    }
}
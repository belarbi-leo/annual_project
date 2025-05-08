package ecodeli.tools;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@NoArgsConstructor
public class PasswordHasher {
    private String password;
    private String hashedPassword;

    public PasswordHasher(String password) {
        this.password = password;
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        this.hashedPassword = encoder.encode(password);
    }

    public static String hashPassword(String password) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static Boolean comparePassword(String password, String hashedPassword) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, hashedPassword);
    }

    public Boolean comparePassword(String password) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, this.hashedPassword);
    }

}

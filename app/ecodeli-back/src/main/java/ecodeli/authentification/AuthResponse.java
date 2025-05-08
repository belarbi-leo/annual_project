package ecodeli.authentification;

import ecodeli.DTO.read.RequestsServicesDTORead;
import ecodeli.enumeratation.AccountStatusEnum;
import ecodeli.enumeratation.RoleEnum;
import lombok.Data;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;

import java.util.List;
import java.util.Map;

@Profile("prod")
@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private AccountStatusEnum accountStatus;
    private RoleEnum role;
    private List<RequestsServicesDTORead> requestsServices;
    //private Map<Integer, String> nameSvc;
}

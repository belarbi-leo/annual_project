package ecodeli.DTO.read;

import ecodeli.enumeratation.AuthorizationSvcEnum;
import ecodeli.enumeratation.CategorySvcEnum;
import ecodeli.modele.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class ServicesDTORead {
    private Integer idSvc;
    private Users adminCreator;
    private Timestamp dateCreationSvc;
    private String nameSvc;
    private CategorySvcEnum category;
    private AuthorizationSvcEnum auth;
}

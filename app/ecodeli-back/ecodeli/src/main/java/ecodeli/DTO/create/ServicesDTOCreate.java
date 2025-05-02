package ecodeli.DTO.create;

import ecodeli.enumeratation.AuthorizationSvcEnum;
import ecodeli.enumeratation.CategorySvcEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class ServicesDTOCreate {
    @NotNull(message = "The admin who created the service must to be informed")
    private Integer adminCreator;
    //DEFAULT now()
    private Timestamp dateCreationSvc;
    @NotBlank(message = "A name for the service is needed")
    @Size(max = 100, message = "Service's name must be less than 100 characters")
    //UNIQUE
    private String nameSvc;
    private CategorySvcEnum category;
    //DEFAULT 'pro'
    private AuthorizationSvcEnum auth;
}

package ecodeli.DTO.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ServicesDocsDTOCreate {
    @NotNull(message = "A service needed to be selected")
    private Integer svc;
    @NotBlank(message = "Document need a name")
    @Size(max = 100, message = "Document's name must be less than 100 characters")
    //UNIQUE
    private String nameDoc;
}

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
public class MaterielsDTOCreate {
    @NotNull(message = "Service must be informed")
    private Integer svc;
    @NotBlank(message = "Materiel need a name")
    @Size(max = 100, message = "Materiel's name must be less than 100 characters")
    private String nameMateriel;
    private String descriptionMateriel;
}

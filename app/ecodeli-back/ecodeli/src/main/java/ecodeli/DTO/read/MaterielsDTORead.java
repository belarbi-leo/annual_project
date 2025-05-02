package ecodeli.DTO.read;

import ecodeli.modele.Services;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MaterielsDTORead {
    private Integer idMateriel;
    private Services svc;
    private String nameMateriel;
    private String descriptionMateriel;
}

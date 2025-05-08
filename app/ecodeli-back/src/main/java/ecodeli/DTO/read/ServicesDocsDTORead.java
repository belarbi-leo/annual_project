package ecodeli.DTO.read;

import ecodeli.modele.Services;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ServicesDocsDTORead {
    private Integer idDocSvc;
    private Services svc;
    private String nameDoc;
}

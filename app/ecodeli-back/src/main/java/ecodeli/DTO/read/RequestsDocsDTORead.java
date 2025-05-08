package ecodeli.DTO.read;

import ecodeli.modele.RequestsServices;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestsDocsDTORead {
    private Integer idDocReq;
    private RequestsServices reqSvc;
    private String docReq;
    private String comment;
}

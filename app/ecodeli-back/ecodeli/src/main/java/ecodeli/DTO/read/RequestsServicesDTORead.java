package ecodeli.DTO.read;

import ecodeli.enumeratation.StatusReqSvcEnum;
import ecodeli.modele.Services;
import ecodeli.modele.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class RequestsServicesDTORead {
    private Integer idReqSvc;
    private Users userReq;
    private Users adminRes;
    private Services svc;
    private StatusReqSvcEnum statusReq;
    private Timestamp dateReq;
    private Timestamp dateRes;
    private String reasonRes;
}

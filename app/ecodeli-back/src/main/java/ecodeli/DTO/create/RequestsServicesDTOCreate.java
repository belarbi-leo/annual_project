package ecodeli.DTO.create;

import ecodeli.enumeratation.StatusReqSvcEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class RequestsServicesDTOCreate {
    @NotNull(message = "The user that made the request must be informed")
    private Integer userReq;
    private Integer adminRes;
    @NotNull(message = "The service concerned must be informed")
    private Integer svc;
    //DEFAULT 'pending'
    private StatusReqSvcEnum statusReq;
    //DEFAULT now()
    private Timestamp dateReq;
    private Timestamp dateRes;
    @Size(max = 255, message = "Reason request must be less than 255 characters")
    private String reasonRes;
}

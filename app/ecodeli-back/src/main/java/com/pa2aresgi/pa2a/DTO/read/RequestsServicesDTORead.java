package com.pa2aresgi.pa2a.DTO.read;

import com.pa2aresgi.pa2a.enumeratation.StatusReqSvcEnum;
import com.pa2aresgi.pa2a.modele.Services;
import com.pa2aresgi.pa2a.modele.Users;
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

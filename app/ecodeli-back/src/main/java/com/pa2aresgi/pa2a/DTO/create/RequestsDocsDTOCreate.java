package com.pa2aresgi.pa2a.DTO.create;

import com.pa2aresgi.pa2a.modele.RequestsServices;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestsDocsDTOCreate {
    private RequestsServices reqSvc;
    private String docReq;
    private String comment;
}

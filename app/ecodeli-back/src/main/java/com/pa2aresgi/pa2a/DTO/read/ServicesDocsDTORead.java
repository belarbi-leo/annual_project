package com.pa2aresgi.pa2a.DTO.read;

import com.pa2aresgi.pa2a.modele.Services;
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

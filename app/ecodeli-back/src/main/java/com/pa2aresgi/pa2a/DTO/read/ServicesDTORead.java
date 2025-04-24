package com.pa2aresgi.pa2a.DTO.read;

import com.pa2aresgi.pa2a.enumeratation.AuthorizationSvcEnum;
import com.pa2aresgi.pa2a.enumeratation.CategorySvcEnum;
import com.pa2aresgi.pa2a.modele.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class ServicesDTORead {
    private Integer idSvc;
    private Users adminCreator;
    private Timestamp dateCreationSvc;
    private String nameSvc;
    private CategorySvcEnum category;
    private AuthorizationSvcEnum auth;
}

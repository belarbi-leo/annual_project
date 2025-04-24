package com.pa2aresgi.pa2a.DTO.create;

import com.pa2aresgi.pa2a.modele.Services;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MaterielsDTOCreate {
    private Services svc;
    private String nameMateriel;
    private String descriptionMateriel;
}

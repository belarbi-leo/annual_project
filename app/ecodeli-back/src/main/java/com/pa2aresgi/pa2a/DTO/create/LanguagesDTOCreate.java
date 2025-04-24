package com.pa2aresgi.pa2a.DTO.create;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LanguagesDTOCreate {
    private String name;
    private String iso;
    private Boolean available;
}

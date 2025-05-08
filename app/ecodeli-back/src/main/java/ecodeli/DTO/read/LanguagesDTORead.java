package ecodeli.DTO.read;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class LanguagesDTORead {
    private Integer idLanguage;
    private String name;
    private String iso;
    private Boolean available;
}

package ecodeli.DTO.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LanguagesDTOCreate {
    @NotBlank(message = "Language must have a name")
    @Size(max = 30, message = "Language's name must be less than 30 characters")
    //UNIQUE
    private String name;
    @NotBlank(message = "ISO must be informed")
    @Size(min = 2, max = 2, message = "ISO must be 2 characters")
    //UNIQUE
    private String iso;
    //DEFAULT false
    private Boolean available;
}

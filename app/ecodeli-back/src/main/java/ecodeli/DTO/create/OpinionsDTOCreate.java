package ecodeli.DTO.create;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class OpinionsDTOCreate {
    @NotNull(message="An ad is required")
    private Integer ad;
    @Max(5)
    private short noteOpinion;
    @NotBlank(message = "A title is required for the opinion")
    @Size(max = 255, message = "Title must be less than 255 characters")
    private String titleOpinion;
    @NotBlank(message = "Opinion's description is required")
    private String descriptionOpinion;
    //DEFAULT now()
    private Timestamp dateOpinion;
}

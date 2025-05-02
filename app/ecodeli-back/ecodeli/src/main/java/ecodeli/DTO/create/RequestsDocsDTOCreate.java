package ecodeli.DTO.create;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestsDocsDTOCreate {
    @NotNull(message = "The requested service must be informed")
    private Integer reqSvc;
    private String docReq;
    @Size(max = 255, message = "Comment must be less than 255 characters")
    private String comment;
}

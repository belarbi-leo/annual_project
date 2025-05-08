package ecodeli.DTO.create;

import ecodeli.enumeratation.StatusDisputeEnum;
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
public class DisputesDTOCreate {
    @NotNull(message = "Ad must be informed")
    private Integer ad;
    @NotNull(message = "User must be informed")
    private Integer user;
    //DEFAULT now()
    private Timestamp dateStatusDispute;
    //DEFAULT 'pending'
    private StatusDisputeEnum statusDispute;
    @NotBlank(message = "Description for the dispute is needed")
    @Size(max = 2048, message = "Description for the dispute must be less than 2048 characters")
    private String descriptionDispute;
    private Timestamp datesStartDispute;
    private Timestamp dateEndDispute;
    private String photoDispute;
    private String resolutionText;
}

package ecodeli.DTO.create;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PackagesDTOCreate {
    @NotNull(message="Ad must be informed")
    private Integer ad;
    @Size(max = 1024, message = " The pack's content must be less than 1024 characters")
    private String contentPack;
    //pas de not null, remplacement du null par le DEFAULT 1 dans le code
    private Integer quantityPack;
    @Size(max = 255, message = "Details must be less than 255 characters")
    private String detailsPack;
    private Float weightPack;
    private Integer lengthPack;
    private Integer widthPack;
    private Integer heightPack;
    private String photoPack;
    //DEFAULT false
    private Boolean fragile;
}

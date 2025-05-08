package ecodeli.DTO.read;

import ecodeli.modele.Ads;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class OpinionsDTORead {
    private Integer idOpinion;
    private Ads ad;
    private short noteOpinion;
    private String titleOpinion;
    private String descriptionOpinion;
    private Timestamp dateOpinion;
}

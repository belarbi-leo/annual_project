package ecodeli.DTO.read;

import ecodeli.enumeratation.StatusDisputeEnum;
import ecodeli.modele.Ads;
import ecodeli.modele.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class DisputesDTORead {
    private Integer idDispute;
    private Ads ad;
    private Users user;
    private Timestamp dateStatusDispute;
    private StatusDisputeEnum statusDispute;
    private String descriptionDispute;
    private Timestamp datesStartDispute;
    private Timestamp dateEndDispute;
    private String photoDispute;
    private String resolutionText;
}

package ecodeli.DTO.read;

import ecodeli.modele.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NFCDTORead {
    private Integer idCard;
    private Users user;
}

package com.pa2aresgi.pa2a.DTO.read;

import com.pa2aresgi.pa2a.modele.Users;
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

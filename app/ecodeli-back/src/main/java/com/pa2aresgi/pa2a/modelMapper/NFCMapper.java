package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.NFCDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.NFCDTORead;
import com.pa2aresgi.pa2a.modele.NFC;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NFCMapper {
    public NFCDTORead toDtoRead(NFC nfc);
    public NFC fromDtoCreate(NFCDTOCreate nfcDtoCreate);
}

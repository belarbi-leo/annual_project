package ecodeli.modelMapper;

import ecodeli.DTO.create.NFCDTOCreate;
import ecodeli.DTO.read.NFCDTORead;
import ecodeli.modele.NFC;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NFCMapper {
    public NFCDTORead toDtoRead(NFC nfc);

    @Mapping(target = "user", ignore = true)
    public NFC fromDtoCreate(NFCDTOCreate nfcDtoCreate);

    @Mapping(target = "user", ignore = true)
    public NFC updateNFCFromDtoCreate(NFCDTOCreate nfcDtoCreate, @MappingTarget NFC nfc);
}

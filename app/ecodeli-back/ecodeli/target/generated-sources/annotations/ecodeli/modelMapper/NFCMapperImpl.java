package ecodeli.modelMapper;

import ecodeli.DTO.create.NFCDTOCreate;
import ecodeli.DTO.read.NFCDTORead;
import ecodeli.modele.NFC;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-07T13:42:24+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.2 (Homebrew)"
)
@Component
public class NFCMapperImpl implements NFCMapper {

    @Override
    public NFCDTORead toDtoRead(NFC nfc) {
        if ( nfc == null ) {
            return null;
        }

        NFCDTORead nFCDTORead = new NFCDTORead();

        nFCDTORead.setIdCard( nfc.getIdCard() );
        nFCDTORead.setUser( nfc.getUser() );

        return nFCDTORead;
    }

    @Override
    public NFC fromDtoCreate(NFCDTOCreate nfcDtoCreate) {
        if ( nfcDtoCreate == null ) {
            return null;
        }

        NFC nFC = new NFC();

        return nFC;
    }

    @Override
    public NFC updateNFCFromDtoCreate(NFCDTOCreate nfcDtoCreate, NFC nfc) {
        if ( nfcDtoCreate == null ) {
            return nfc;
        }

        return nfc;
    }
}

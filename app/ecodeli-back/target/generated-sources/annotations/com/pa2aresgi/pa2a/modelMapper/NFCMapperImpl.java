package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.NFCDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.NFCDTORead;
import com.pa2aresgi.pa2a.modele.NFC;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-25T10:18:00+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
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

        nFC.setUser( nfcDtoCreate.getUser() );

        return nFC;
    }
}

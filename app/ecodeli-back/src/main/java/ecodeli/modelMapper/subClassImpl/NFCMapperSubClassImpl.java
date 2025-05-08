package ecodeli.modelMapper.subClassImpl;

import ecodeli.DTO.create.NFCDTOCreate;
import ecodeli.DTO.read.NFCDTORead;
import ecodeli.modelMapper.NFCMapper;
import ecodeli.modele.NFC;
import ecodeli.modele.Users;
import ecodeli.repository.UsersRepository;
import ecodeli.tools.ResponseErrorV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NFCMapperSubClassImpl {
    private final NFCMapper nfcMapper;
    private final UsersRepository usersRepository;

    public NFCDTORead toDtoRead(NFC nfc){
        return nfcMapper.toDtoRead(nfc);
    }

    public Object fromDtoCreate(NFCDTOCreate nfcDtoCreate){
        NFC nfc = nfcMapper.fromDtoCreate(nfcDtoCreate);
        ResponseErrorV2 retourNeg = new ResponseErrorV2();
        if (nfcDtoCreate.getUser() != null){
            Optional<Users> user = usersRepository.findById(nfcDtoCreate.getUser());
            if (user.isEmpty()) retourNeg.add("user", "Id user not found !");
            else nfc.setUser(user.get());
        }
        if (!retourNeg.getErrors().isEmpty())
            return retourNeg;
        else
            return nfc;
    }

    public Object updateNFCFromDtoCreate(NFCDTOCreate nfcDtoCreate, NFC nfc){
        NFC nfc2 = nfcMapper.updateNFCFromDtoCreate(nfcDtoCreate, nfc);
        ResponseErrorV2 retourNeg = new ResponseErrorV2();
        if (nfcDtoCreate.getUser() != null){
            Optional<Users> user = usersRepository.findById(nfcDtoCreate.getUser());
            if (user.isEmpty()) retourNeg.add("user", "Id user not found !");
            else nfc2.setUser(user.get());
        }
        if (!retourNeg.getErrors().isEmpty())
            return retourNeg;
        else
            return nfc2;
    }
}

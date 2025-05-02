package ecodeli.DTOService;

import ecodeli.DTO.create.NFCDTOCreate;
import ecodeli.DTO.read.NFCDTORead;
import ecodeli.modelMapper.subClassImpl.NFCMapperSubClassImpl;
import ecodeli.modele.NFC;
import ecodeli.repository.NFCRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NFCDTOServiceImpl implements NFCDTOService {
    private NFCRepository nfcRepository;
    @Autowired
    private NFCMapperSubClassImpl nfcMapper;

    @Override
    public Object create(NFCDTOCreate nfcDtoCreate){
        Object obj = nfcMapper.fromDtoCreate(nfcDtoCreate);
        if (obj instanceof NFC)
            return nfcMapper.toDtoRead(nfcRepository.save((NFC) obj));
        else
            return obj;
    }

    @Override
    public List<NFCDTORead> readAll(){
        return nfcRepository.findAll().stream().map(nfcMapper::toDtoRead).toList();
    }

    @Override
    public List<NFCDTORead> readAll(Sort sort){
        return nfcRepository.findAll(sort).stream().map(nfcMapper::toDtoRead).toList();
    }

    @Override
    public Slice<NFCDTORead> readAll(Pageable pageParam){
        return nfcRepository.findAll(pageParam).map(nfcMapper::toDtoRead);
    }

    @Override
    public Optional<NFCDTORead> findById(Integer id){
        return nfcRepository.findById(id).map(nfcMapper::toDtoRead);
    }

    @Override
    public Optional<Object> update(Integer id, NFCDTOCreate nfcDTOCreate){
        return nfcRepository.findById(id).map(nfc -> {
            Object obj = nfcMapper.updateNFCFromDtoCreate(nfcDTOCreate, nfc);
            if (obj instanceof NFC)
                return nfcMapper.toDtoRead(nfcRepository.save((NFC) obj));
            else
                return obj;
        });
    }

    @Override
    public Boolean deleteById(Integer id){
        if (nfcRepository.existsById(id)){
            nfcRepository.deleteById(id);
            return true;
        } else
            return false;
    }
}

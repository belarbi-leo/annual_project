package ecodeli.DTOService;

import ecodeli.DTO.create.LanguagesDTOCreate;
import ecodeli.DTO.read.LanguagesDTORead;
import ecodeli.modelMapper.LanguagesMapper;
import ecodeli.repository.LanguagesRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LanguagesDTOServiceImpl implements LanguagesDTOService {
    private LanguagesRepository languagesRepository;
    private LanguagesMapper languagesMapper;

    @Override
    public LanguagesDTORead create(LanguagesDTOCreate languageDtoCreate){
        languageDtoCreate.setIso(languageDtoCreate.getIso().toUpperCase());
        if (languageDtoCreate.getAvailable() == null) languageDtoCreate.setAvailable(false);
        return languagesMapper.toDtoRead(languagesRepository.save(languagesMapper.fromDtoCreate(languageDtoCreate)));
    }

    @Override
    public List<LanguagesDTORead> readAll(){
        return languagesRepository.findAll().stream().map(languagesMapper::toDtoRead).toList();
    }

    @Override
    public List<LanguagesDTORead> readAll(Sort sort){
        return languagesRepository.findAll(sort).stream().map(languagesMapper::toDtoRead).toList();
    }

    @Override
    public Slice<LanguagesDTORead> readAll(Pageable pageParam){
        return languagesRepository.findAll(pageParam).map(languagesMapper::toDtoRead);
    }

    @Override
    public Optional<LanguagesDTORead> findById(Integer id){
        return languagesRepository.findById(id).map(languagesMapper::toDtoRead);
    }

    @Override
    public Optional<LanguagesDTORead> findByIso(String iso){
        return languagesRepository.findByIso(iso.toUpperCase()).map(languagesMapper::toDtoRead);
    }

    @Override
    public Optional<LanguagesDTORead> update(Integer id, LanguagesDTOCreate languageDtoCreate){
        languageDtoCreate.setIso(languageDtoCreate.getIso().toUpperCase());
        return languagesRepository.findById(id).map(language -> languagesMapper.toDtoRead(languagesRepository.save(languagesMapper.updateDtoCreate(languageDtoCreate, language))));
    }

    @Override
    public Optional<LanguagesDTORead> update(String iso, LanguagesDTOCreate languageDtoCreate){
        languageDtoCreate.setIso(languageDtoCreate.getIso().toUpperCase());
        return languagesRepository.findByIso(iso.toUpperCase()).map(language -> languagesMapper.toDtoRead(languagesRepository.save(languagesMapper.updateDtoCreate(languageDtoCreate, language))));
    }

    @Override
    public Boolean deleteById(Integer id){
        if (languagesRepository.existsById(id)){
            languagesRepository.deleteById(id);
            return true;
        } else
            return false;
    }

    @Override
    @Transactional
    public Boolean deleteByIso(String iso){
        if (languagesRepository.existsByIso(iso.toUpperCase())){
            languagesRepository.deleteByIso(iso.toUpperCase());
            return true;
        } else
            return false;
    }
}

package ecodeli.DTOService;

import ecodeli.DTO.create.PackagesDTOCreate;
import ecodeli.DTO.read.PackagesDTORead;
import ecodeli.modelMapper.subClassImpl.PackagesMapperSubClassImpl;
import ecodeli.modele.Packages;
import ecodeli.repository.PackagesRepository;
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
public class PackagesDTOServiceImpl implements PackagesDTOService {
    private PackagesRepository packagesRepository;
    @Autowired
    private PackagesMapperSubClassImpl packagesMapper;

    @Override
    public Object create(PackagesDTOCreate packDtoCreate){
        if (packDtoCreate.getQuantityPack() == null) packDtoCreate.setQuantityPack(1);
        if (packDtoCreate.getFragile() == null) packDtoCreate.setFragile(false);

        Object obj = packagesMapper.fromDtoCreate(packDtoCreate);
        if (obj instanceof Packages)
            return packagesMapper.toDtoRead(packagesRepository.save((Packages) obj));
        else
            return obj;
    }

    @Override
    public List<PackagesDTORead> readAll(){
        return packagesRepository.findAll().stream().map(packagesMapper::toDtoRead).toList();
    }

    @Override
    public List<PackagesDTORead> readAll(Sort sort){
        return packagesRepository.findAll(sort).stream().map(packagesMapper::toDtoRead).toList();
    }

    @Override
    public Slice<PackagesDTORead> readAll(Pageable pageParam){
        return packagesRepository.findAll(pageParam).map(packagesMapper::toDtoRead);
    }

    @Override
    public Optional<PackagesDTORead> findById(Integer id){
        return packagesRepository.findById(id).map(packagesMapper::toDtoRead);
    }

    @Override
    public Optional<Object> update(Integer id, PackagesDTOCreate packDTOCreate){
        return packagesRepository.findById(id).map(pack -> {
            Object obj = packagesMapper.updatePackageFromDtoCreate(packDTOCreate, pack);
            if (obj instanceof Packages)
                return packagesMapper.toDtoRead(packagesRepository.save((Packages) obj));
            else
                return obj;
        });
    }

    @Override
    public Boolean deleteById(Integer id){
        if (packagesRepository.existsById(id)){
            packagesRepository.deleteById(id);
            return true;
        } else
            return false;
    }
}

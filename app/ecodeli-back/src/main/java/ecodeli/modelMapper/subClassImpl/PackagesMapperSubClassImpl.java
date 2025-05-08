package ecodeli.modelMapper.subClassImpl;

import ecodeli.DTO.create.PackagesDTOCreate;
import ecodeli.DTO.read.PackagesDTORead;
import ecodeli.modelMapper.PackagesMapper;
import ecodeli.modele.Ads;
import ecodeli.modele.Packages;
import ecodeli.repository.AdsRepository;
import ecodeli.tools.ResponseErrorV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PackagesMapperSubClassImpl {
    private final PackagesMapper packagesMapper;
    private final AdsRepository adsRepository;

    public PackagesDTORead toDtoRead(Packages pack){
        return packagesMapper.toDtoRead(pack);
    }

    public Object fromDtoCreate(PackagesDTOCreate packageDtoCreate){
        Packages pack = packagesMapper.fromDtoCreate(packageDtoCreate);
        Optional<Ads> ad = adsRepository.findById(packageDtoCreate.getAd());
        ResponseErrorV2 retourNeg = new ResponseErrorV2();
        if (ad.isEmpty()) retourNeg.add("ad", "Id ad not found !");
        else pack.setAd(ad.get());
        if (!retourNeg.getErrors().isEmpty())
            return retourNeg;
        else
            return pack;
    }

    public Object updatePackageFromDtoCreate(PackagesDTOCreate packageDtoCreate, Packages pack){
        Packages pack2 = packagesMapper.updatePackageFromDtoCreate(packageDtoCreate, pack);
        ResponseErrorV2 retourNeg = new ResponseErrorV2();
        if (packageDtoCreate.getAd() != null){
            Optional<Ads> ad = adsRepository.findById(packageDtoCreate.getAd());
            if (ad.isEmpty()) retourNeg.add("ad", "Id ad not found !");
            else pack2.setAd(ad.get());
        }
        if (!retourNeg.getErrors().isEmpty())
            return retourNeg;
        else
            return pack2;
    }
}

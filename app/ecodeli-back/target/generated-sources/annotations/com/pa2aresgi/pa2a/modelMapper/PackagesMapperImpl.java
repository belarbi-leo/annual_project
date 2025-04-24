package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.PackagesDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.PackagesDTORead;
import com.pa2aresgi.pa2a.modele.Packages;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-24T16:32:19+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class PackagesMapperImpl implements PackagesMapper {

    @Override
    public PackagesDTORead toDtoRead(Packages pack) {
        if ( pack == null ) {
            return null;
        }

        PackagesDTORead packagesDTORead = new PackagesDTORead();

        packagesDTORead.setAd( pack.getAd() );
        packagesDTORead.setContentPack( pack.getContentPack() );
        packagesDTORead.setDetailsPack( pack.getDetailsPack() );
        packagesDTORead.setFragile( pack.getFragile() );
        packagesDTORead.setHeightPack( pack.getHeightPack() );
        packagesDTORead.setIdPack( pack.getIdPack() );
        packagesDTORead.setLengthPack( pack.getLengthPack() );
        packagesDTORead.setPhotoPack( pack.getPhotoPack() );
        packagesDTORead.setQuantityPack( pack.getQuantityPack() );
        packagesDTORead.setWeightPack( pack.getWeightPack() );
        packagesDTORead.setWidthPack( pack.getWidthPack() );

        return packagesDTORead;
    }

    @Override
    public Packages fromDtoCreate(PackagesDTOCreate packageDtoCreate) {
        if ( packageDtoCreate == null ) {
            return null;
        }

        Packages packages = new Packages();

        packages.setAd( packageDtoCreate.getAd() );
        packages.setContentPack( packageDtoCreate.getContentPack() );
        packages.setDetailsPack( packageDtoCreate.getDetailsPack() );
        packages.setFragile( packageDtoCreate.getFragile() );
        packages.setHeightPack( packageDtoCreate.getHeightPack() );
        packages.setLengthPack( packageDtoCreate.getLengthPack() );
        packages.setPhotoPack( packageDtoCreate.getPhotoPack() );
        packages.setQuantityPack( packageDtoCreate.getQuantityPack() );
        packages.setWeightPack( packageDtoCreate.getWeightPack() );
        packages.setWidthPack( packageDtoCreate.getWidthPack() );

        return packages;
    }
}

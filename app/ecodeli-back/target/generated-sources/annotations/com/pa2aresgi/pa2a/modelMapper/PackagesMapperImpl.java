package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.PackagesDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.PackagesDTORead;
import com.pa2aresgi.pa2a.modele.Packages;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-02T20:10:08+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.2 (Homebrew)"
)
@Component
public class PackagesMapperImpl implements PackagesMapper {

    @Override
    public PackagesDTORead toDtoRead(Packages pack) {
        if ( pack == null ) {
            return null;
        }

        PackagesDTORead packagesDTORead = new PackagesDTORead();

        packagesDTORead.setIdPack( pack.getIdPack() );
        packagesDTORead.setAd( pack.getAd() );
        packagesDTORead.setContentPack( pack.getContentPack() );
        packagesDTORead.setQuantityPack( pack.getQuantityPack() );
        packagesDTORead.setDetailsPack( pack.getDetailsPack() );
        packagesDTORead.setWeightPack( pack.getWeightPack() );
        packagesDTORead.setLengthPack( pack.getLengthPack() );
        packagesDTORead.setWidthPack( pack.getWidthPack() );
        packagesDTORead.setHeightPack( pack.getHeightPack() );
        packagesDTORead.setPhotoPack( pack.getPhotoPack() );
        packagesDTORead.setFragile( pack.getFragile() );

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
        packages.setQuantityPack( packageDtoCreate.getQuantityPack() );
        packages.setDetailsPack( packageDtoCreate.getDetailsPack() );
        packages.setWeightPack( packageDtoCreate.getWeightPack() );
        packages.setLengthPack( packageDtoCreate.getLengthPack() );
        packages.setWidthPack( packageDtoCreate.getWidthPack() );
        packages.setHeightPack( packageDtoCreate.getHeightPack() );
        packages.setPhotoPack( packageDtoCreate.getPhotoPack() );
        packages.setFragile( packageDtoCreate.getFragile() );

        return packages;
    }
}

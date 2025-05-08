package ecodeli.modelMapper;

import ecodeli.DTO.create.PackagesDTOCreate;
import ecodeli.DTO.read.PackagesDTORead;
import ecodeli.modele.Packages;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-08T15:25:52+0200",
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

    @Override
    public Packages updatePackageFromDtoCreate(PackagesDTOCreate packageDtoCreate, Packages pack) {
        if ( packageDtoCreate == null ) {
            return pack;
        }

        if ( packageDtoCreate.getContentPack() != null ) {
            pack.setContentPack( packageDtoCreate.getContentPack() );
        }
        if ( packageDtoCreate.getDetailsPack() != null ) {
            pack.setDetailsPack( packageDtoCreate.getDetailsPack() );
        }
        if ( packageDtoCreate.getFragile() != null ) {
            pack.setFragile( packageDtoCreate.getFragile() );
        }
        if ( packageDtoCreate.getHeightPack() != null ) {
            pack.setHeightPack( packageDtoCreate.getHeightPack() );
        }
        if ( packageDtoCreate.getLengthPack() != null ) {
            pack.setLengthPack( packageDtoCreate.getLengthPack() );
        }
        if ( packageDtoCreate.getPhotoPack() != null ) {
            pack.setPhotoPack( packageDtoCreate.getPhotoPack() );
        }
        if ( packageDtoCreate.getQuantityPack() != null ) {
            pack.setQuantityPack( packageDtoCreate.getQuantityPack() );
        }
        if ( packageDtoCreate.getWeightPack() != null ) {
            pack.setWeightPack( packageDtoCreate.getWeightPack() );
        }
        if ( packageDtoCreate.getWidthPack() != null ) {
            pack.setWidthPack( packageDtoCreate.getWidthPack() );
        }

        return pack;
    }
}

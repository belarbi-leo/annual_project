package ecodeli.modelMapper;

import ecodeli.DTO.create.PackagesDTOCreate;
import ecodeli.DTO.read.PackagesDTORead;
import ecodeli.modele.Packages;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-04T16:32:48+0200",
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

    @Override
    public Packages updatePackageFromDtoCreate(PackagesDTOCreate packageDtoCreate, Packages pack) {
        if ( packageDtoCreate == null ) {
            return pack;
        }

        if ( packageDtoCreate.getContentPack() != null ) {
            pack.setContentPack( packageDtoCreate.getContentPack() );
        }
        if ( packageDtoCreate.getQuantityPack() != null ) {
            pack.setQuantityPack( packageDtoCreate.getQuantityPack() );
        }
        if ( packageDtoCreate.getDetailsPack() != null ) {
            pack.setDetailsPack( packageDtoCreate.getDetailsPack() );
        }
        if ( packageDtoCreate.getWeightPack() != null ) {
            pack.setWeightPack( packageDtoCreate.getWeightPack() );
        }
        if ( packageDtoCreate.getLengthPack() != null ) {
            pack.setLengthPack( packageDtoCreate.getLengthPack() );
        }
        if ( packageDtoCreate.getWidthPack() != null ) {
            pack.setWidthPack( packageDtoCreate.getWidthPack() );
        }
        if ( packageDtoCreate.getHeightPack() != null ) {
            pack.setHeightPack( packageDtoCreate.getHeightPack() );
        }
        if ( packageDtoCreate.getPhotoPack() != null ) {
            pack.setPhotoPack( packageDtoCreate.getPhotoPack() );
        }
        if ( packageDtoCreate.getFragile() != null ) {
            pack.setFragile( packageDtoCreate.getFragile() );
        }

        return pack;
    }
}

package ecodeli.modelMapper;

import ecodeli.DTO.create.PackagesDTOCreate;
import ecodeli.DTO.read.PackagesDTORead;
import ecodeli.modele.Packages;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PackagesMapper {
    public PackagesDTORead toDtoRead(Packages pack);

    @Mapping(target = "ad", ignore = true)
    public Packages fromDtoCreate(PackagesDTOCreate packageDtoCreate);

    @Mapping(target = "ad", ignore = true)
    public Packages updatePackageFromDtoCreate(PackagesDTOCreate packageDtoCreate, @MappingTarget Packages pack);
}

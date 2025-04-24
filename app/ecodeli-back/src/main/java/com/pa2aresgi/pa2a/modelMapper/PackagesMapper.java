package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.PackagesDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.PackagesDTORead;
import com.pa2aresgi.pa2a.modele.Packages;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PackagesMapper {
    public PackagesDTORead toDtoRead(Packages pack);
    public Packages fromDtoCreate(PackagesDTOCreate packageDtoCreate);
}

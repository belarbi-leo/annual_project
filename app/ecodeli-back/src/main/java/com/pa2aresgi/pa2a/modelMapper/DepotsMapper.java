package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.DepotsDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.DepotsDTORead;
import com.pa2aresgi.pa2a.modele.Depots;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepotsMapper {
    public DepotsDTORead toDtoRead(Depots depot);
    public Depots fromDtoCreate(DepotsDTOCreate depotDtoCreate);
}

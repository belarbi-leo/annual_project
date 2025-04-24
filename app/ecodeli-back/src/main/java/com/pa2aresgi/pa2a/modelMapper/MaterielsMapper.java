package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.MaterielsDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.MaterielsDTORead;
import com.pa2aresgi.pa2a.modele.Materiels;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MaterielsMapper {
    public MaterielsDTORead toDtoRead(Materiels materiel);
    public Materiels fromDtoCreate(MaterielsDTOCreate materielDtoCreate);
}

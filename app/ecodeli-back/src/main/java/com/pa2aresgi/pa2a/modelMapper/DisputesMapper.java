package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.DisputesDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.DisputesDTORead;
import com.pa2aresgi.pa2a.modele.Disputes;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DisputesMapper {
    public DisputesDTORead toDtoRead(Disputes dispute);
    public Disputes fromDtoCreate(DisputesDTOCreate disputeDtoCreate);
}

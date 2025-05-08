package ecodeli.modelMapper;

import ecodeli.DTO.create.MaterielsDTOCreate;
import ecodeli.DTO.read.MaterielsDTORead;
import ecodeli.modele.Materiels;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MaterielsMapper {
    public MaterielsDTORead toDtoRead(Materiels materiel);

    @Mapping(target = "svc", ignore = true)
    public Materiels fromDtoCreate(MaterielsDTOCreate materielDtoCreate);

    @Mapping(target = "svc", ignore = true)
    public Materiels updateMaterielFromDtoCreate(MaterielsDTOCreate materielDtoCreate, @MappingTarget Materiels materiel);
}

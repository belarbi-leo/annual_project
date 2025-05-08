package ecodeli.modelMapper;

import ecodeli.DTO.create.DisputesDTOCreate;
import ecodeli.DTO.read.DisputesDTORead;
import ecodeli.modele.Disputes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DisputesMapper {
    public DisputesDTORead toDtoRead(Disputes dispute);

    @Mapping(target = "ad", ignore = true)
    @Mapping(target = "user", ignore = true)
    public Disputes fromDtoCreate(DisputesDTOCreate disputeDtoCreate);

    @Mapping(target = "ad", ignore = true)
    @Mapping(target = "user", ignore = true)
    public Disputes updateDisputeFromDtoCreate(DisputesDTOCreate disputeDtoCreate, @MappingTarget Disputes dispute);
}

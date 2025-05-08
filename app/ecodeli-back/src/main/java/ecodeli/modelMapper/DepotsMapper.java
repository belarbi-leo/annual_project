package ecodeli.modelMapper;

import ecodeli.DTO.create.DepotsDTOCreate;
import ecodeli.DTO.read.DepotsDTORead;
import ecodeli.modele.Depots;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DepotsMapper {
    public DepotsDTORead toDtoRead(Depots depot);
    public Depots fromDtoCreate(DepotsDTOCreate depotDtoCreate);
    public Depots updateDepotFromDtoCreate(DepotsDTOCreate depotDtoCreate, @MappingTarget Depots depot);
}

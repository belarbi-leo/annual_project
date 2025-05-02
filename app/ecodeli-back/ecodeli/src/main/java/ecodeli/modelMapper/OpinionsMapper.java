package ecodeli.modelMapper;

import ecodeli.DTO.create.OpinionsDTOCreate;
import ecodeli.DTO.read.OpinionsDTORead;
import ecodeli.modele.Opinions;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OpinionsMapper {
    public OpinionsDTORead toDtoRead(Opinions opinion);

    @Mapping(target = "ad", ignore = true)
    public Opinions fromDtoCreate(OpinionsDTOCreate opinionDtoCreate);

    @Mapping(target = "ad", ignore = true)
    public Opinions updateOpinionFromDtoCreate(OpinionsDTOCreate opinionDtoCreate, @MappingTarget Opinions opinion);
}

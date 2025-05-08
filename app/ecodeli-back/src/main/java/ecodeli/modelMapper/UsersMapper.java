package ecodeli.modelMapper;

import ecodeli.DTO.create.UsersDTOCreate;
import ecodeli.DTO.read.UsersDTORead;
import ecodeli.modele.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UsersMapper {
    public UsersDTORead toDtoRead(Users user);

    @Mapping(target = "subscription", ignore = true)
    @Mapping(target = "language", ignore = true)
    public Users fromDtoCreate(UsersDTOCreate userDtoCreate);

    @Mapping(target = "subscription", ignore = true)
    @Mapping(target = "language", ignore = true)
    public Users updateUserFromDtoCreate(UsersDTOCreate userDtoCreate, @MappingTarget Users user);
}

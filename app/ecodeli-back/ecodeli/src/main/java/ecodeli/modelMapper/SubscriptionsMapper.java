package ecodeli.modelMapper;

import ecodeli.DTO.create.SubscriptionsDTOCreate;
import ecodeli.DTO.read.SubscriptionsDTORead;
import ecodeli.modele.Subscriptions;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SubscriptionsMapper {
    public SubscriptionsDTORead toDtoRead(Subscriptions subscription);
    public Subscriptions fromDtoCreate(SubscriptionsDTOCreate subscriptionDtoCreate);
    public Subscriptions updateSubscriptionFromDtoCreate(SubscriptionsDTOCreate subscriptionDtoCreate, @MappingTarget Subscriptions subscription);
}

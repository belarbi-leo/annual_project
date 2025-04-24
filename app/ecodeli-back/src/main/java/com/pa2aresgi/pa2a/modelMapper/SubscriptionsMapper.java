package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.SubscriptionsDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.SubscriptionsDTORead;
import com.pa2aresgi.pa2a.modele.Subscriptions;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubscriptionsMapper {
    public SubscriptionsDTORead toDtoRead(Subscriptions subscription);
    public Subscriptions fromDtoCreate(SubscriptionsDTOCreate subscriptionDtoCreate);
}

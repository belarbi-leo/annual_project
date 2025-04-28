package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.SubscriptionsDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.SubscriptionsDTORead;
import com.pa2aresgi.pa2a.modele.Subscriptions;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-27T21:40:52+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class SubscriptionsMapperImpl implements SubscriptionsMapper {

    @Override
    public SubscriptionsDTORead toDtoRead(Subscriptions subscription) {
        if ( subscription == null ) {
            return null;
        }

        SubscriptionsDTORead subscriptionsDTORead = new SubscriptionsDTORead();

        subscriptionsDTORead.setDescriptionSub( subscription.getDescriptionSub() );
        subscriptionsDTORead.setIdSubscription( subscription.getIdSubscription() );
        subscriptionsDTORead.setInsurance( subscription.getInsurance() );
        subscriptionsDTORead.setNameSub( subscription.getNameSub() );
        subscriptionsDTORead.setPrice( subscription.getPrice() );
        subscriptionsDTORead.setSendPriority( subscription.getSendPriority() );
        subscriptionsDTORead.setShippingReduction( subscription.getShippingReduction() );

        return subscriptionsDTORead;
    }

    @Override
    public Subscriptions fromDtoCreate(SubscriptionsDTOCreate subscriptionDtoCreate) {
        if ( subscriptionDtoCreate == null ) {
            return null;
        }

        Subscriptions subscriptions = new Subscriptions();

        subscriptions.setDescriptionSub( subscriptionDtoCreate.getDescriptionSub() );
        subscriptions.setInsurance( subscriptionDtoCreate.getInsurance() );
        subscriptions.setNameSub( subscriptionDtoCreate.getNameSub() );
        subscriptions.setPrice( subscriptionDtoCreate.getPrice() );
        subscriptions.setSendPriority( subscriptionDtoCreate.getSendPriority() );
        subscriptions.setShippingReduction( subscriptionDtoCreate.getShippingReduction() );

        return subscriptions;
    }
}

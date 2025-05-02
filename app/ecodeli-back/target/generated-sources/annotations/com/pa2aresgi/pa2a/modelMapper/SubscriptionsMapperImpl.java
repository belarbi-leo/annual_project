package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.SubscriptionsDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.SubscriptionsDTORead;
import com.pa2aresgi.pa2a.modele.Subscriptions;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-28T23:10:07+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.2 (Homebrew)"
)
@Component
public class SubscriptionsMapperImpl implements SubscriptionsMapper {

    @Override
    public SubscriptionsDTORead toDtoRead(Subscriptions subscription) {
        if ( subscription == null ) {
            return null;
        }

        SubscriptionsDTORead subscriptionsDTORead = new SubscriptionsDTORead();

        subscriptionsDTORead.setIdSubscription( subscription.getIdSubscription() );
        subscriptionsDTORead.setNameSub( subscription.getNameSub() );
        subscriptionsDTORead.setDescriptionSub( subscription.getDescriptionSub() );
        subscriptionsDTORead.setPrice( subscription.getPrice() );
        subscriptionsDTORead.setInsurance( subscription.getInsurance() );
        subscriptionsDTORead.setShippingReduction( subscription.getShippingReduction() );
        subscriptionsDTORead.setSendPriority( subscription.getSendPriority() );

        return subscriptionsDTORead;
    }

    @Override
    public Subscriptions fromDtoCreate(SubscriptionsDTOCreate subscriptionDtoCreate) {
        if ( subscriptionDtoCreate == null ) {
            return null;
        }

        Subscriptions subscriptions = new Subscriptions();

        subscriptions.setNameSub( subscriptionDtoCreate.getNameSub() );
        subscriptions.setDescriptionSub( subscriptionDtoCreate.getDescriptionSub() );
        subscriptions.setPrice( subscriptionDtoCreate.getPrice() );
        subscriptions.setInsurance( subscriptionDtoCreate.getInsurance() );
        subscriptions.setShippingReduction( subscriptionDtoCreate.getShippingReduction() );
        subscriptions.setSendPriority( subscriptionDtoCreate.getSendPriority() );

        return subscriptions;
    }
}

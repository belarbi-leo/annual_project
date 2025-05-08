package ecodeli.modelMapper;

import ecodeli.DTO.create.SubscriptionsDTOCreate;
import ecodeli.DTO.read.SubscriptionsDTORead;
import ecodeli.modele.Subscriptions;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-08T15:25:52+0200",
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

        subscriptionsDTORead.setActive( subscription.getActive() );
        subscriptionsDTORead.setDescriptionSub( subscription.getDescriptionSub() );
        subscriptionsDTORead.setIdSubscription( subscription.getIdSubscription() );
        subscriptionsDTORead.setInsurance( subscription.getInsurance() );
        subscriptionsDTORead.setNameSub( subscription.getNameSub() );
        subscriptionsDTORead.setPermanentReduction( subscription.getPermanentReduction() );
        subscriptionsDTORead.setPrice( subscription.getPrice() );
        subscriptionsDTORead.setSendPriority( subscription.getSendPriority() );
        subscriptionsDTORead.setShippingReduction( subscription.getShippingReduction() );
        subscriptionsDTORead.setTargetAudience( subscription.getTargetAudience() );

        return subscriptionsDTORead;
    }

    @Override
    public Subscriptions fromDtoCreate(SubscriptionsDTOCreate subscriptionDtoCreate) {
        if ( subscriptionDtoCreate == null ) {
            return null;
        }

        Subscriptions subscriptions = new Subscriptions();

        subscriptions.setActive( subscriptionDtoCreate.getActive() );
        subscriptions.setDescriptionSub( subscriptionDtoCreate.getDescriptionSub() );
        subscriptions.setInsurance( subscriptionDtoCreate.getInsurance() );
        subscriptions.setNameSub( subscriptionDtoCreate.getNameSub() );
        subscriptions.setPermanentReduction( subscriptionDtoCreate.getPermanentReduction() );
        subscriptions.setPrice( subscriptionDtoCreate.getPrice() );
        subscriptions.setSendPriority( subscriptionDtoCreate.getSendPriority() );
        subscriptions.setShippingReduction( subscriptionDtoCreate.getShippingReduction() );
        subscriptions.setTargetAudience( subscriptionDtoCreate.getTargetAudience() );

        return subscriptions;
    }

    @Override
    public Subscriptions updateSubscriptionFromDtoCreate(SubscriptionsDTOCreate subscriptionDtoCreate, Subscriptions subscription) {
        if ( subscriptionDtoCreate == null ) {
            return subscription;
        }

        if ( subscriptionDtoCreate.getActive() != null ) {
            subscription.setActive( subscriptionDtoCreate.getActive() );
        }
        if ( subscriptionDtoCreate.getDescriptionSub() != null ) {
            subscription.setDescriptionSub( subscriptionDtoCreate.getDescriptionSub() );
        }
        if ( subscriptionDtoCreate.getInsurance() != null ) {
            subscription.setInsurance( subscriptionDtoCreate.getInsurance() );
        }
        if ( subscriptionDtoCreate.getNameSub() != null ) {
            subscription.setNameSub( subscriptionDtoCreate.getNameSub() );
        }
        if ( subscriptionDtoCreate.getPermanentReduction() != null ) {
            subscription.setPermanentReduction( subscriptionDtoCreate.getPermanentReduction() );
        }
        if ( subscriptionDtoCreate.getPrice() != null ) {
            subscription.setPrice( subscriptionDtoCreate.getPrice() );
        }
        if ( subscriptionDtoCreate.getSendPriority() != null ) {
            subscription.setSendPriority( subscriptionDtoCreate.getSendPriority() );
        }
        if ( subscriptionDtoCreate.getShippingReduction() != null ) {
            subscription.setShippingReduction( subscriptionDtoCreate.getShippingReduction() );
        }
        if ( subscriptionDtoCreate.getTargetAudience() != null ) {
            subscription.setTargetAudience( subscriptionDtoCreate.getTargetAudience() );
        }

        return subscription;
    }
}

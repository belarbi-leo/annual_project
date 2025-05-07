package ecodeli.modelMapper;

import ecodeli.DTO.create.SubscriptionsDTOCreate;
import ecodeli.DTO.read.SubscriptionsDTORead;
import ecodeli.modele.Subscriptions;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-07T13:42:24+0200",
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
        subscriptionsDTORead.setPermanentReduction( subscription.getPermanentReduction() );
        subscriptionsDTORead.setSendPriority( subscription.getSendPriority() );
        subscriptionsDTORead.setTargetAudience( subscription.getTargetAudience() );
        subscriptionsDTORead.setActive( subscription.getActive() );

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
        subscriptions.setPermanentReduction( subscriptionDtoCreate.getPermanentReduction() );
        subscriptions.setSendPriority( subscriptionDtoCreate.getSendPriority() );
        subscriptions.setTargetAudience( subscriptionDtoCreate.getTargetAudience() );
        subscriptions.setActive( subscriptionDtoCreate.getActive() );

        return subscriptions;
    }

    @Override
    public Subscriptions updateSubscriptionFromDtoCreate(SubscriptionsDTOCreate subscriptionDtoCreate, Subscriptions subscription) {
        if ( subscriptionDtoCreate == null ) {
            return subscription;
        }

        if ( subscriptionDtoCreate.getNameSub() != null ) {
            subscription.setNameSub( subscriptionDtoCreate.getNameSub() );
        }
        if ( subscriptionDtoCreate.getDescriptionSub() != null ) {
            subscription.setDescriptionSub( subscriptionDtoCreate.getDescriptionSub() );
        }
        if ( subscriptionDtoCreate.getPrice() != null ) {
            subscription.setPrice( subscriptionDtoCreate.getPrice() );
        }
        if ( subscriptionDtoCreate.getInsurance() != null ) {
            subscription.setInsurance( subscriptionDtoCreate.getInsurance() );
        }
        if ( subscriptionDtoCreate.getShippingReduction() != null ) {
            subscription.setShippingReduction( subscriptionDtoCreate.getShippingReduction() );
        }
        if ( subscriptionDtoCreate.getPermanentReduction() != null ) {
            subscription.setPermanentReduction( subscriptionDtoCreate.getPermanentReduction() );
        }
        if ( subscriptionDtoCreate.getSendPriority() != null ) {
            subscription.setSendPriority( subscriptionDtoCreate.getSendPriority() );
        }
        if ( subscriptionDtoCreate.getTargetAudience() != null ) {
            subscription.setTargetAudience( subscriptionDtoCreate.getTargetAudience() );
        }
        if ( subscriptionDtoCreate.getActive() != null ) {
            subscription.setActive( subscriptionDtoCreate.getActive() );
        }

        return subscription;
    }
}

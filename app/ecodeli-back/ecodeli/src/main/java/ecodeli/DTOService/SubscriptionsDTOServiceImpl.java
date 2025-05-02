package ecodeli.DTOService;

import ecodeli.DTO.create.SubscriptionsDTOCreate;
import ecodeli.DTO.read.SubscriptionsDTORead;
import ecodeli.modelMapper.SubscriptionsMapper;
import ecodeli.repository.SubscriptionsRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SubscriptionsDTOServiceImpl implements SubscriptionsDTOService {
    private SubscriptionsRepository subscriptionsRepository;
    private SubscriptionsMapper subscriptionsMapper;

    @Override
    public SubscriptionsDTORead create(SubscriptionsDTOCreate subscriptionDtoCreate){
        if (subscriptionDtoCreate.getPrice() == null) subscriptionDtoCreate.setPrice((float) 0.0);
        if (subscriptionDtoCreate.getInsurance() == null) subscriptionDtoCreate.setInsurance((float) 0.0);
        if (subscriptionDtoCreate.getShippingReduction() == null) subscriptionDtoCreate.setShippingReduction(0);
        if (subscriptionDtoCreate.getPermanentReduction() == null) subscriptionDtoCreate.setPermanentReduction(0);
        if (subscriptionDtoCreate.getSendPriority() == null) subscriptionDtoCreate.setSendPriority(0);

        return subscriptionsMapper.toDtoRead(subscriptionsRepository.save(subscriptionsMapper.fromDtoCreate(subscriptionDtoCreate)));
    }

    @Override
    public List<SubscriptionsDTORead> readAll(){
        return subscriptionsRepository.findAll().stream().map(subscriptionsMapper::toDtoRead).toList();
    }

    @Override
    public List<SubscriptionsDTORead> readAll(Sort sort){
        return subscriptionsRepository.findAll(sort).stream().map(subscriptionsMapper::toDtoRead).toList();
    }

    @Override
    public Slice<SubscriptionsDTORead> readAll(Pageable pageParam){
        return subscriptionsRepository.findAll(pageParam).map(subscriptionsMapper::toDtoRead);
    }

    @Override
    public Optional<SubscriptionsDTORead> findById(Integer id){
        return subscriptionsRepository.findById(id).map(subscriptionsMapper::toDtoRead);
        /*Subscriptions subscription = subscriptionsRepository.findById(id).orElse(null);
        if (subscription != null)
            return Optional.of(subscriptionsMapper.toDtoRead(subscription));
        else
            return Optional.empty();*/
    }

    @Override
    public Optional<SubscriptionsDTORead> update(Integer id, SubscriptionsDTOCreate subscriptionDtoCreate){
        return subscriptionsRepository.findById(id).map(sub->subscriptionsMapper.toDtoRead(subscriptionsRepository.save(subscriptionsMapper.updateSubscriptionFromDtoCreate(subscriptionDtoCreate, sub))));
    }

    @Override
    public Boolean deleteById(Integer id){
        if (subscriptionsRepository.existsById(id)){
            subscriptionsRepository.deleteById(id);
            return true;
        } else
            return false;
    }
}

package com.pa2aresgi.pa2a.DTO.create;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SubscriptionsDTOCreate {
    private String nameSub;
    private String descriptionSub;
    private Float price;
    private Float insurance;
    private Integer shippingReduction;
    private Integer sendPriority;
}

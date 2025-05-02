package ecodeli.DTO.read;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SubscriptionsDTORead {
    private Integer idSubscription;
    private String nameSub;
    private String descriptionSub;
    private Float price;
    private Float insurance;
    private Integer shippingReduction;
    private Integer permanentReduction;
    private Integer sendPriority;
}

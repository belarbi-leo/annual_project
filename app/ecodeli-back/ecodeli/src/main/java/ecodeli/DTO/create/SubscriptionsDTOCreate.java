package ecodeli.DTO.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SubscriptionsDTOCreate {
    @NotBlank(message = "A name is required for the subscription")
    @Size(max = 50, message = "The subscription name must be less than 50 characters")
    //UNIQUE
    private String nameSub;
    private String descriptionSub;
    //DEFAULT 0.0
    private Float price;
    //DEFAULT 0.0
    private Float insurance;
    //DEFAULT 0
    private Integer shippingReduction;
    //DEFAULT 0
    private Integer permanentReduction;
    //DEFAULT 0
    private Integer sendPriority;
}

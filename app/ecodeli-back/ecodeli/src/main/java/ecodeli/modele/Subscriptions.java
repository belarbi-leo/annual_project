package ecodeli.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="subscriptions")
@Getter
@Setter
@NoArgsConstructor
public class Subscriptions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_sub")
    private Integer idSubscription;
    @Column(name="name_sub", length = 50)
    private String nameSub;
    @Column(name="description_sub", columnDefinition="text")
    private String descriptionSub;
    @Column(name="price", columnDefinition="decimal(10,2)")
    private Float price;
    @Column(name="insurance", columnDefinition="decimal(10,2)")
    private Float insurance;
    @Column(name="shipping_reduction")
    private Integer shippingReduction;
    @Column(name="permanent_reduction")
    private Integer permanentReduction;
    @Column(name="send_priority")
    private Integer sendPriority;
    @OneToMany(mappedBy = "subscription")
    @JsonIgnore
    private List<Users> usersList = new ArrayList<>();
    /*@OneToMany(mappedBy = "id_sub")
    @JsonIgnore
    private List<Ads> ads_list = new ArrayList<>();*/
}

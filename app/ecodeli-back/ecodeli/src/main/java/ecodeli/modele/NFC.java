package ecodeli.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="nfc")
@Getter
@Setter
@NoArgsConstructor
public class NFC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_card_nfc")
    private Integer idCard;
    @OneToOne
    @JoinColumn(name="id_user")
    private Users user;
}

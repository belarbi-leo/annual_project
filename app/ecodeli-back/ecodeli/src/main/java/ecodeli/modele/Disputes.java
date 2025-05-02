package ecodeli.modele;

import ecodeli.enumeratation.StatusDisputeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name="disputes")
@Getter
@Setter
@NoArgsConstructor
public class Disputes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_dispute")
    private Integer idDispute;
    @ManyToOne
    @JoinColumn(name="id_ad", nullable = false)
    private Ads ad;
    @ManyToOne
    @JoinColumn(name="id_user", nullable = false)
    private Users user;
    @Column(name="date_status_dispute")
    private Timestamp dateStatusDispute;
    @Column(name="status_dispute")
    @Enumerated(EnumType.STRING)
    private StatusDisputeEnum statusDispute;
    @Column(name="description_dispute", columnDefinition="text")
    private String descriptionDispute;
    @Column(name="date_start_dispute")
    private Timestamp dateStartDispute;
    @Column(name="date_end_dispute")
    private Timestamp dateEndDispute;
    @Column(name="photo_dispute", columnDefinition="text")
    private String photoDispute;
    @Column(name="resolution_text", columnDefinition="text")
    private String resolutionText;
}

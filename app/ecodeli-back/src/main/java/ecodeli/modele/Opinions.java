package ecodeli.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name="opinions")
@Getter
@Setter
@NoArgsConstructor
public class Opinions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_opinion")
    private Integer idOpinion;
    @ManyToOne
    @JoinColumn(name="id_ad", nullable = false)
    private Ads ad;
    @Column(name="note_opinion")
    private short noteOpinion;
    @Column(name="title_opinion", length = 255)
    private String titleOpinion;
    @Column(name="description_opinion", columnDefinition="text")
    private String descriptionOpinion;
    @Column(name="date_opinion")
    private Timestamp dateOpinion;
}

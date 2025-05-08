package ecodeli.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="materiels")
@Getter
@Setter
@NoArgsConstructor
public class Materiels {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_mat")
    private Integer idMateriel;
    @ManyToOne
    @JoinColumn(name="id_svc", nullable = false)
    private Services svc;
    @Column(name="name_mat", length=100)
    private String nameMateriel;
    @Column(name="description_mat", columnDefinition="text")
    private String descriptionMateriel;
}

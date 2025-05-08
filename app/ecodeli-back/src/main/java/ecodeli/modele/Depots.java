package ecodeli.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="depots")
@Getter
@Setter
@NoArgsConstructor
public class Depots {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_depot")
    private Integer idDepot;
    @Column(name="storage_capacity_depot")
    private Integer storageCapacityDepot;
    /*@Column(length = 255)
    private String street_depot;*/
    @Column(name="location", length=255)
    private String location;
    @Column(name="suite", length = 255)
    private String suite;
    @Column(name="locality", length=255)
    private String locality;
    @Column(name="state", length = 255)
    private String state;
    @Column(name="postal_code", length = 20)    //pourquoi 20 si normalement c'est max 6 ?
    private String postalCode;
    @Column(name="country", length = 100)
    private String country;
    @Column(name="latitude", columnDefinition = "decimal(9,6)")
    private Float latitude;
    @Column(name="longitude", columnDefinition = "decimal(9,6)")
    private Float longitude;
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "stock_control",
            joinColumns = @JoinColumn(name="id_depot"),
            inverseJoinColumns = @JoinColumn(name="id_pack"))
    @JsonIgnore
    private Set<Packages> packagesSet = new HashSet<>();
}

package com.pa2aresgi.pa2a.modele;

import com.pa2aresgi.pa2a.enumeratation.Status_dispute_enum;
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
    private Integer id_dispute;
    @ManyToOne
    @JoinColumn(name="id_ad", nullable = false)
    private Ads id_ad;
    @ManyToOne
    @JoinColumn(name="id_user", nullable = false)
    private Users id_user;
    @Column
    private Timestamp date_status_dispute;
    @Column
    @Enumerated(EnumType.STRING)
    private Status_dispute_enum status_dispute;
    @Column
    private String description_dispute;
    @Column
    private Timestamp date_start_dispute;
    @Column
    private Timestamp date_end_dispute;
    @Column
    private String photo_dispute;
    @Column
    private String resolution_text;
}

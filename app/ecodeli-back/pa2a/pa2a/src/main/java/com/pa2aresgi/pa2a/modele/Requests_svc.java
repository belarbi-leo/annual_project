package com.pa2aresgi.pa2a.modele;

import com.pa2aresgi.pa2a.enumeratation.Status_req_svc_enum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="requests_svc")
@Getter
@Setter
@NoArgsConstructor
public class Requests_svc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_req_svc;
    @ManyToOne
    @JoinColumn(name="id_user", nullable = false)
    private Users id_user;
    @ManyToOne
    @JoinColumn(name="id_user")
    private Users id_amdin_res;
    @ManyToOne
    @JoinColumn(name="id_svc", nullable = false)
    private Services id_svc;
    @Column
    @Enumerated(EnumType.STRING)
    private Status_req_svc_enum status_req;
    @Column
    private Timestamp date_req;
    @Column
    private Timestamp date_res;
    @Column(length=255)
    private String reason_res;
    @OneToMany(mappedBy = "requests_svc")
    private List<Requests_docs> requests_docs_list = new ArrayList<>();
}

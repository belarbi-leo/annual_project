package ecodeli.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ecodeli.enumeratation.StatusReqSvcEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="requests_services")
@Getter
@Setter
@NoArgsConstructor
public class RequestsServices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_req_svc")
    private Integer idReqSvc;
    @ManyToOne
    @JoinColumn(name="id_user_req", nullable = false)
    private Users userReq;
    @ManyToOne
    @JoinColumn(name="id_admin_res")
    private Users adminRes;
    @ManyToOne
    @JoinColumn(name="id_svc", nullable = false)
    private Services svc;
    @Column(name="status_req")
    @Enumerated(EnumType.STRING)
    private StatusReqSvcEnum statusReq;
    @Column(name="date_req")
    private Timestamp dateReq;
    @Column(name="date_res")
    private Timestamp dateRes;
    @Column(name="reason_res", length=255)
    private String reasonRes;
    @OneToMany(mappedBy = "reqSvc")
    @JsonIgnore
    private List<RequestsDocs> requestsDocsList = new ArrayList<>();
}

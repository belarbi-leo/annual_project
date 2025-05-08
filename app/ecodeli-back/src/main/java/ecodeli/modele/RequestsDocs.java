package ecodeli.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="requests_docs")
@Getter
@Setter
@NoArgsConstructor
public class RequestsDocs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_doc_req")
    private Integer idDocReq;
    @ManyToOne
    @JoinColumn(name="id_req_svc", nullable = false)
    private RequestsServices reqSvc;
    /*@Column(length=100)
    private String doc_type_req;*/
    @Column(name="doc_req", columnDefinition="text")
    private String docReq;
    @Column(name="comment", length=255)
    private String comment;
}

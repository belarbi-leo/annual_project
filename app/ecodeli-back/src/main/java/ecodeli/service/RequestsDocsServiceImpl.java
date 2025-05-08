package ecodeli.service;

import ecodeli.modele.RequestsDocs;
import ecodeli.repository.RequestsDocsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RequestsDocsServiceImpl implements RequestsDocsService {
    private RequestsDocsRepository requestsDocsRepository;

    @Override
    public RequestsDocs create(RequestsDocs requestDoc) {
        return requestsDocsRepository.save(requestDoc);
    }

    @Override
    public List<RequestsDocs> readAll() {
        return requestsDocsRepository.findAll();
    }

    @Override
    public List<RequestsDocs> readAllOrderById() {
        return requestsDocsRepository.findAllOrderById_doc_req();
    }

    @Override
    public RequestsDocs findById(Integer id) {
        if (requestsDocsRepository.findById(id).isPresent()){
            return requestsDocsRepository.findById(id).get();
        } else {
            throw new RuntimeException("RequestDoc not found ! ");
        }
    }

    @Override
    public RequestsDocs update(Integer id, RequestsDocs requestDoc) {
        return requestsDocsRepository.findById(id).map(rqDoc -> {
            rqDoc.setReqSvc(requestDoc.getReqSvc());
            //rq_doc.setDoc_type_req(request_doc.getDoc_type_req());
            rqDoc.setDocReq(requestDoc.getDocReq());
            rqDoc.setComment(requestDoc.getComment());
            return requestsDocsRepository.save(rqDoc);
        }).orElseThrow(() -> new RuntimeException("RequestDoc not found !"));
    }

    @Override
    public String deleteById(Integer id) {
        requestsDocsRepository.deleteById(id);
        return "RequestDoc deleted !";
    }
}
package ecodeli.DTOController;

import ecodeli.DTO.create.RequestsServicesDTOCreate;
import ecodeli.DTO.read.RequestsServicesDTORead;
import ecodeli.DTOService.RequestsServicesDTOService;
import ecodeli.tools.ResponseBody;
import ecodeli.tools.ResponseErrorV2;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@Profile({"dev", "prod"})
@RestController
@RequestMapping("/requestsServices${api.suffixe}")
@AllArgsConstructor
public class RequestsServicesDTOController {

    private final RequestsServicesDTOService requestsServicesDTOService;
    private final String nameId = "idReqSvc";

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody RequestsServicesDTOCreate requestServiceDTOCreate){
        Object obj = requestsServicesDTOService.create(requestServiceDTOCreate);
        if (obj instanceof RequestsServicesDTORead){
            URI location = URI.create("/requestsServices/" + ((RequestsServicesDTORead)obj).getIdReqSvc());
            return ResponseEntity.created(location).body(obj);
        } else {
            return ResponseEntity.badRequest().body(obj);
        }
    }

    @GetMapping("/read")
    public ResponseEntity<Object> readAll(
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String paging
    ){
        Sort sorting = Sort.by(Sort.Direction.ASC,nameId);
        if (sort != null) {
            if (sort.equals("desc")) sorting = Sort.by(Sort.Direction.DESC,nameId);
            else if (sort.equals("none")) sorting = Sort.unsorted();
        }
        if ("none".equals(paging)) return ResponseEntity.ok(requestsServicesDTOService.readAll(sorting));
        else return ResponseEntity.ok(requestsServicesDTOService.readAll(PageRequest.of((page!=null && page>=0)?page:0, (size!=null && size >0)?size:10, sorting)));
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id){
        Optional<RequestsServicesDTORead> requestService = requestsServicesDTOService.findById(id);
        if (requestService.isPresent()) return ResponseEntity.ok(requestService.get());
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"RequestService not found !"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody RequestsServicesDTOCreate requestServiceDTOCreate) {
        Optional<Object> requestService = requestsServicesDTOService.update(id, requestServiceDTOCreate);
        if (requestService.isPresent()) {
            if (requestService.get() instanceof RequestsServicesDTORead) return ResponseEntity.ok(requestService.get());
            else return ResponseEntity.badRequest().body(requestService.get());
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"RequestService not found !"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        if (requestsServicesDTOService.deleteById(id)) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseBody("RequestService successfully deleted !"));
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"RequestService not found !"));
    }
}

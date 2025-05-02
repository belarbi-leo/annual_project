package ecodeli.DTOController;

import ecodeli.DTO.create.RequestsDocsDTOCreate;
import ecodeli.DTO.read.RequestsDocsDTORead;
import ecodeli.DTOService.RequestsDocsDTOService;
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
@RequestMapping("/requestsDocs${api.suffixe}")
@AllArgsConstructor
public class RequestsDocsDTOController {

    private final RequestsDocsDTOService requestsDocsDTOService;
    private final String nameId = "idDocReq";

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody RequestsDocsDTOCreate requestDocDTOCreate){
        Object obj = requestsDocsDTOService.create(requestDocDTOCreate);
        if (obj instanceof RequestsDocsDTORead){
            URI location = URI.create("/requestsDocs/" + ((RequestsDocsDTORead)obj).getIdDocReq());
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
        if ("none".equals(paging)) return ResponseEntity.ok(requestsDocsDTOService.readAll(sorting));
        else return ResponseEntity.ok(requestsDocsDTOService.readAll(PageRequest.of((page!=null && page>=0)?page:0, (size!=null && size >0)?size:10, sorting)));
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id){
        Optional<RequestsDocsDTORead> requestDoc = requestsDocsDTOService.findById(id);
        if (requestDoc.isPresent()) return ResponseEntity.ok(requestDoc.get());
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"RequestDoc not found !"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody RequestsDocsDTOCreate requestDocDTOCreate) {
        Optional<Object> requestDoc = requestsDocsDTOService.update(id, requestDocDTOCreate);
        if (requestDoc.isPresent()) {
            if (requestDoc.get() instanceof RequestsDocsDTORead) return ResponseEntity.ok(requestDoc.get());
            else return ResponseEntity.badRequest().body(requestDoc.get());
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"RequestDoc not found !"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        if (requestsDocsDTOService.deleteById(id)) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseBody("RequestDoc successfully deleted !"));
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"RequestDoc not found !"));
    }
}

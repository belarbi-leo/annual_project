package ecodeli.DTOController;

import ecodeli.DTO.create.DisputesDTOCreate;
import ecodeli.DTO.read.DisputesDTORead;
import ecodeli.DTOService.DisputesDTOService;
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
@RequestMapping("/disputes${api.suffixe}")
@AllArgsConstructor
public class DisputesDTOController {

    private final DisputesDTOService disputesDTOService;
    private final String nameId = "idDispute";

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody DisputesDTOCreate disputeDTOCreate){
        Object obj = disputesDTOService.create(disputeDTOCreate);
        if (obj instanceof DisputesDTORead){
            URI location = URI.create("/disputes/" + ((DisputesDTORead)obj).getIdDispute());
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
        if ("none".equals(paging)) return ResponseEntity.ok(disputesDTOService.readAll(sorting));
        else return ResponseEntity.ok(disputesDTOService.readAll(PageRequest.of((page!=null && page>=0)?page:0, (size!=null && size >0)?size:10, sorting)));
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id){
        Optional<DisputesDTORead> dispute = disputesDTOService.findById(id);
        if (dispute.isPresent()) return ResponseEntity.ok(dispute.get());
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"Dispute not found !"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody DisputesDTOCreate disputeDTOCreate) {
        Optional<Object> dispute = disputesDTOService.update(id, disputeDTOCreate);
        if (dispute.isPresent()) {
            if (dispute.get() instanceof DisputesDTORead) return ResponseEntity.ok(dispute.get());
            else return ResponseEntity.badRequest().body(dispute.get());
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"Dispute not found !"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        if (disputesDTOService.deleteById(id)) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseBody("Dispute successfully deleted !"));
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"Dispute not found !"));
    }
}

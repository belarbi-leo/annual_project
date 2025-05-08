package ecodeli.DTOController;

import ecodeli.DTO.create.ServicesDocsDTOCreate;
import ecodeli.DTO.read.ServicesDocsDTORead;
import ecodeli.DTOService.ServicesDocsDTOService;
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
@RequestMapping("/servicesDocs${api.suffixe}")
@AllArgsConstructor
public class ServicesDocsDTOController {
    private final ServicesDocsDTOService servicesDocsDTOService;
    private final String nameId = "idDocSvc";

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody ServicesDocsDTOCreate serviceDocDTOCreate){
        Object obj = servicesDocsDTOService.create(serviceDocDTOCreate);
        if (obj instanceof ServicesDocsDTORead){
            URI location = URI.create("/servicesDocs/" + ((ServicesDocsDTORead)obj).getIdDocSvc());
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
    ) {
        Sort sorting = Sort.by(Sort.Direction.ASC,nameId);
        if (sort != null) {
            if (sort.equals("desc")) sorting = Sort.by(Sort.Direction.DESC,nameId);
            else if (sort.equals("none")) sorting = Sort.unsorted();
        }
        if ("none".equals(paging)) return ResponseEntity.ok(servicesDocsDTOService.readAll(sorting));
        else return ResponseEntity.ok(servicesDocsDTOService.readAll(PageRequest.of((page!=null && page>=0)?page:0, (size!=null && size >0)?size:10, sorting)));

    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id){
        Optional<ServicesDocsDTORead> serviceDoc = servicesDocsDTOService.findById(id);
        if (serviceDoc.isPresent()) return ResponseEntity.ok(serviceDoc.get());
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"ServiceDoc not found !"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody ServicesDocsDTOCreate serviceDocDTOCreate) {
        Optional<Object> serviceDoc = servicesDocsDTOService.update(id, serviceDocDTOCreate);
        if (serviceDoc.isPresent()) {
            if (serviceDoc.get() instanceof ServicesDocsDTORead) return ResponseEntity.ok(serviceDoc.get());
            else return ResponseEntity.badRequest().body(serviceDoc.get());
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"ServiceDoc not found !"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        if (servicesDocsDTOService.deleteById(id)) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseBody("ServiceDoc successfully deleted !"));
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"ServiceDoc not found !"));
    }
}

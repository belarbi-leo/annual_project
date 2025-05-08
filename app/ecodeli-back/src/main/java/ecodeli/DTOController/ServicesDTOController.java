package ecodeli.DTOController;

import ecodeli.DTO.create.ServicesDTOCreate;
import ecodeli.DTO.read.ServicesDTORead;
import ecodeli.DTOService.ServicesDTOService;
import ecodeli.enumeratation.AuthorizationSvcEnum;
import ecodeli.enumeratation.RoleEnum;
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
import java.util.Set;

@Profile({"dev", "prod"})
@RestController
@RequestMapping("/services${api.suffixe}")
@AllArgsConstructor
public class ServicesDTOController {

    private final ServicesDTOService servicesDTOService;
    private final String nameId = "idSvc";

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody ServicesDTOCreate serviceDTOCreate){
        Object obj = servicesDTOService.create(serviceDTOCreate);
        if (obj instanceof ServicesDTORead){
            URI location = URI.create("/services/" + ((ServicesDTORead)obj).getIdSvc());
            return ResponseEntity.created(location).body(obj);
        } else {
            return ResponseEntity.badRequest().body(obj);
        }
    }

    @GetMapping("/read")
    public ResponseEntity<Object> readAll(
            @RequestParam(name = "auth", required = false) Set<AuthorizationSvcEnum> auth,
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
        if (auth != null && !auth.isEmpty()) {
            if ("none".equals(paging)) return ResponseEntity.ok(servicesDTOService.readAllByAuthIn(auth,sorting));
            else return ResponseEntity.ok(servicesDTOService.readAllByAuthIn(auth, PageRequest.of((page!=null && page>=0)?page:0, (size!=null && size >0)?size:10, sorting)));
        } else {
            if ("none".equals(paging)) return ResponseEntity.ok(servicesDTOService.readAll(sorting));
            else return ResponseEntity.ok(servicesDTOService.readAll(PageRequest.of((page!=null && page>=0)?page:0, (size!=null && size >0)?size:10, sorting)));
        }
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id){
        Optional<ServicesDTORead> service = servicesDTOService.findById(id);
        if (service.isPresent()) return ResponseEntity.ok(service.get());
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"Service not found !"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody ServicesDTOCreate serviceDTOCreate) {
        Optional<Object> service = servicesDTOService.update(id, serviceDTOCreate);
        if (service.isPresent()) {
            if (service.get() instanceof ServicesDTORead) return ResponseEntity.ok(service.get());
            else return ResponseEntity.badRequest().body(service.get());
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"Service not found !"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        if (servicesDTOService.deleteById(id)) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseBody("Service successfully deleted !"));
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"Service not found !"));
    }
}

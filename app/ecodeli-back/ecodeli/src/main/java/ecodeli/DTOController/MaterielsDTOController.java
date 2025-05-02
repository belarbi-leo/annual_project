package ecodeli.DTOController;

import ecodeli.DTO.create.MaterielsDTOCreate;
import ecodeli.DTO.read.MaterielsDTORead;
import ecodeli.DTOService.MaterielsDTOService;
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
@RequestMapping("/materiels${api.suffixe}")
@AllArgsConstructor
public class MaterielsDTOController {

    private final MaterielsDTOService materielsDTOService;
    private final String nameId = "idMateriel";

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody MaterielsDTOCreate materielDTOCreate){
        Object obj = materielsDTOService.create(materielDTOCreate);
        if (obj instanceof MaterielsDTORead){
            URI location = URI.create("/materiels/" + ((MaterielsDTORead)obj).getIdMateriel());
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
        if ("none".equals(paging)) return ResponseEntity.ok(materielsDTOService.readAll(sorting));
        else return ResponseEntity.ok(materielsDTOService.readAll(PageRequest.of((page!=null && page>=0)?page:0, (size!=null && size >0)?size:10, sorting)));
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id){
        Optional<MaterielsDTORead> materiel = materielsDTOService.findById(id);
        if (materiel.isPresent()) return ResponseEntity.ok(materiel.get());
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"Materiel not found !"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody MaterielsDTOCreate materielDTOCreate) {
        Optional<Object> materiel = materielsDTOService.update(id, materielDTOCreate);
        if (materiel.isPresent()) {
            if (materiel.get() instanceof MaterielsDTORead) return ResponseEntity.ok(materiel.get());
            else return ResponseEntity.badRequest().body(materiel.get());
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"Materiel not found !"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        if (materielsDTOService.deleteById(id)) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseBody("Materiel successfully deleted !"));
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"Materiel not found !"));
    }
}

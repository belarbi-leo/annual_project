package ecodeli.DTOController;

import ecodeli.DTO.create.DepotsDTOCreate;
import ecodeli.DTO.read.DepotsDTORead;
import ecodeli.DTOService.DepotsDTOService;
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
@RequestMapping("/depots${api.suffixe}")
@AllArgsConstructor
public class DepotsDTOController {

    private final DepotsDTOService depotsDTOService;
    private final String nameId = "idDepot";

    @PostMapping("/create")
    public ResponseEntity<DepotsDTORead> create(@Valid @RequestBody DepotsDTOCreate depotDTOCreate){
        DepotsDTORead depotDTORead = depotsDTOService.create(depotDTOCreate);
        URI location = URI.create("/depots/" + (depotDTORead).getIdDepot());
        return ResponseEntity.created(location).body(depotDTORead);
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
        if ("none".equals(paging)) return ResponseEntity.ok(depotsDTOService.readAll(sorting));
        else return ResponseEntity.ok(depotsDTOService.readAll(PageRequest.of((page!=null && page>=0)?page:0, (size!=null && size >0)?size:10, sorting)));
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id){
        Optional<DepotsDTORead> depot = depotsDTOService.findById(id);
        if (depot.isPresent()) return ResponseEntity.ok(depot.get());
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"Depot not found !"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody DepotsDTOCreate depotDTOCreate) {
        Optional<DepotsDTORead> depot = depotsDTOService.update(id, depotDTOCreate);
        if (depot.isPresent()) return ResponseEntity.ok(depot.get());
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"Depot not found !"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        if (depotsDTOService.deleteById(id)) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseBody("Depot successfully deleted !"));
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"Depot not found !"));
    }
}

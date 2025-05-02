package ecodeli.DTOController;

import ecodeli.DTO.create.PackagesDTOCreate;
import ecodeli.DTO.read.PackagesDTORead;
import ecodeli.DTOService.PackagesDTOService;
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
@RequestMapping("/packages${api.suffixe}")
@AllArgsConstructor
public class PackagesDTOController {

    private final PackagesDTOService packagesDTOService;
    private final String nameId = "idPack";

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody PackagesDTOCreate packageDTOCreate){
        Object obj = packagesDTOService.create(packageDTOCreate);
        if (obj instanceof PackagesDTORead){
            URI location = URI.create("/packages/" + ((PackagesDTORead)obj).getIdPack());
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
        if ("none".equals(paging)) return ResponseEntity.ok(packagesDTOService.readAll(sorting));
        else return ResponseEntity.ok(packagesDTOService.readAll(PageRequest.of((page!=null && page>=0)?page:0, (size!=null && size >0)?size:10, sorting)));
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id){
        Optional<PackagesDTORead> pack = packagesDTOService.findById(id);
        if (pack.isPresent()) return ResponseEntity.ok(pack.get());
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"Package not found !"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody PackagesDTOCreate packageDTOCreate) {
        Optional<Object> pack = packagesDTOService.update(id, packageDTOCreate);
        if (pack.isPresent()) {
            if (pack.get() instanceof PackagesDTORead) return ResponseEntity.ok(pack.get());
            else return ResponseEntity.badRequest().body(pack.get());
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"Package not found !"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        if (packagesDTOService.deleteById(id)) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseBody("Package successfully deleted !"));
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"Package not found !"));
    }
}

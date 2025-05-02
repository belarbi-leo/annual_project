package ecodeli.DTOController;

import ecodeli.DTO.create.AdsDTOCreate;
import ecodeli.DTO.read.AdsDTORead;
import ecodeli.DTOService.AdsDTOService;
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
@RequestMapping("/ads${api.suffixe}")
@AllArgsConstructor
public class AdsDTOController {

    private final AdsDTOService adsDTOService;
    private final String nameId = "idAd";

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody AdsDTOCreate adDTOCreate){
        Object obj = adsDTOService.create(adDTOCreate);
        if (obj instanceof AdsDTORead){
            URI location = URI.create("/ads/" + ((AdsDTORead)obj).getIdAd());
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
        if ("none".equals(paging)) return ResponseEntity.ok(adsDTOService.readAll(sorting));
        else return ResponseEntity.ok(adsDTOService.readAll(PageRequest.of((page!=null && page>=0)?page:0, (size!=null && size >0)?size:10, sorting)));
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id){
        Optional<AdsDTORead> ad = adsDTOService.findById(id);
        if (ad.isPresent()) return ResponseEntity.ok(ad.get());
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"Ad not found !"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody AdsDTOCreate adDTOCreate) {
        Optional<Object> ad = adsDTOService.update(id, adDTOCreate);
        if (ad.isPresent()) {
            if (ad.get() instanceof AdsDTORead) return ResponseEntity.ok(ad.get());
            else return ResponseEntity.badRequest().body(ad.get());
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"Ad not found !"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        if (adsDTOService.deleteById(id)) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseBody("Ad successfully deleted !"));
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"Ad not found !"));
    }
}

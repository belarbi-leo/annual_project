package ecodeli.DTOController;

import ecodeli.DTO.create.RequestsAdsDTOCreate;
import ecodeli.DTO.read.RequestsAdsDTORead;
import ecodeli.DTOService.RequestsAdsDTOService;
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
@RequestMapping("/requestsAds${api.suffixe}")
@AllArgsConstructor
public class RequestsAdsDTOController {

    private final RequestsAdsDTOService requestsAdsDTOService;
    private final String nameId = "idReqAd";

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody RequestsAdsDTOCreate requestAdDTOCreate){
        Object obj = requestsAdsDTOService.create(requestAdDTOCreate);
        if (obj instanceof RequestsAdsDTORead){
            URI location = URI.create("/requestsAds/" + ((RequestsAdsDTORead)obj).getIdReqAd());
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
        if ("none".equals(paging)) return ResponseEntity.ok(requestsAdsDTOService.readAll(sorting));
        else return ResponseEntity.ok(requestsAdsDTOService.readAll(PageRequest.of((page!=null && page>=0)?page:0, (size!=null && size >0)?size:10, sorting)));
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id){
        Optional<RequestsAdsDTORead> requestAd = requestsAdsDTOService.findById(id);
        if (requestAd.isPresent()) return ResponseEntity.ok(requestAd.get());
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"RequestAd not found !"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody RequestsAdsDTOCreate requestAdDTOCreate) {
        Optional<Object> requestAd = requestsAdsDTOService.update(id, requestAdDTOCreate);
        if (requestAd.isPresent()) {
            if (requestAd.get() instanceof RequestsAdsDTORead) return ResponseEntity.ok(requestAd.get());
            else return ResponseEntity.badRequest().body(requestAd.get());
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"RequestAd not found !"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        if (requestsAdsDTOService.deleteById(id)) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseBody("RequestAd successfully deleted !"));
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"RequestAd not found !"));
    }
}

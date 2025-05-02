package ecodeli.DTOController;

import ecodeli.DTO.create.NFCDTOCreate;
import ecodeli.DTO.read.NFCDTORead;
import ecodeli.DTOService.NFCDTOService;
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
@RequestMapping("/nfc${api.suffixe}")
@AllArgsConstructor
public class NFCDTOController {

    private final NFCDTOService nfcDTOService;
    private final String nameId = "idCard";

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody NFCDTOCreate nfcDTOCreate){
        Object obj = nfcDTOService.create(nfcDTOCreate);
        if (obj instanceof NFCDTORead){
            URI location = URI.create("/nfc/" + ((NFCDTORead)obj).getIdCard());
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
        if ("none".equals(paging)) return ResponseEntity.ok(nfcDTOService.readAll(sorting));
        else return ResponseEntity.ok(nfcDTOService.readAll(PageRequest.of((page!=null && page>=0)?page:0, (size!=null && size >0)?size:10, sorting)));
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id){
        Optional<NFCDTORead> nfc = nfcDTOService.findById(id);
        if (nfc.isPresent()) return ResponseEntity.ok(nfc.get());
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"NFC not found !"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody NFCDTOCreate nfcDTOCreate) {
        Optional<Object> nfc = nfcDTOService.update(id, nfcDTOCreate);
        if (nfc.isPresent()) {
            if (nfc.get() instanceof NFCDTORead) return ResponseEntity.ok(nfc.get());
            else return ResponseEntity.badRequest().body(nfc.get());
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"NFC not found !"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        if (nfcDTOService.deleteById(id)) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseBody("NFC successfully deleted !"));
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"NFC not found !"));
    }
}

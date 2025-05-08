package ecodeli.DTOController;

import ecodeli.DTO.create.OpinionsDTOCreate;
import ecodeli.DTO.read.OpinionsDTORead;
import ecodeli.DTOService.OpinionsDTOService;
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
@RequestMapping("/opinions${api.suffixe}")
@AllArgsConstructor
public class OpinionsDTOController {

    private final OpinionsDTOService opinionsDTOService;
    private final String nameId = "idOpinion";

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody OpinionsDTOCreate opinionDTOCreate){
        Object obj = opinionsDTOService.create(opinionDTOCreate);
        if (obj instanceof OpinionsDTORead){
            URI location = URI.create("/opinions/" + ((OpinionsDTORead)obj).getIdOpinion());
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
        if ("none".equals(paging)) return ResponseEntity.ok(opinionsDTOService.readAll(sorting));
        else return ResponseEntity.ok(opinionsDTOService.readAll(PageRequest.of((page!=null && page>=0)?page:0, (size!=null && size >0)?size:10, sorting)));
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id){
        Optional<OpinionsDTORead> opinion = opinionsDTOService.findById(id);
        if (opinion.isPresent()) return ResponseEntity.ok(opinion.get());
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"Opinion not found !"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody OpinionsDTOCreate opinionDTOCreate) {
        Optional<Object> opinion = opinionsDTOService.update(id, opinionDTOCreate);
        if (opinion.isPresent()) {
            if (opinion.get() instanceof OpinionsDTORead) return ResponseEntity.ok(opinion.get());
            else return ResponseEntity.badRequest().body(opinion.get());
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"Opinion not found !"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        if (opinionsDTOService.deleteById(id)) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseBody("Opinion successfully deleted !"));
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"Opinion not found !"));
    }
}

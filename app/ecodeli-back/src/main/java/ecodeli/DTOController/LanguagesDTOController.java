package ecodeli.DTOController;

import ecodeli.DTO.create.LanguagesDTOCreate;
import ecodeli.DTO.create.SubscriptionsDTOCreate;
import ecodeli.DTO.read.LanguagesDTORead;
import ecodeli.DTO.read.SubscriptionsDTORead;
import ecodeli.DTOService.LanguagesDTOService;
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
@RequestMapping("/languages${api.suffixe}")
@AllArgsConstructor
public class LanguagesDTOController {

    private LanguagesDTOService languagesDTOService;
    private final String nameId = "idLanguage";

    @PostMapping("/create")
    public ResponseEntity<LanguagesDTORead> create(@Valid @RequestBody LanguagesDTOCreate languageDTOCreate){
        LanguagesDTORead languageDTORead = languagesDTOService.create(languageDTOCreate);
        URI location = URI.create("/languages/" + (languageDTORead).getIdLanguage());
        return ResponseEntity.created(location).body(languageDTORead);
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
        if ("none".equals(paging)) return ResponseEntity.ok(languagesDTOService.readAll(sorting));
        else return ResponseEntity.ok(languagesDTOService.readAll(PageRequest.of((page!=null && page>=0)?page:0, (size!=null && size >0)?size:10, sorting)));
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id){
        Optional<LanguagesDTORead> language = languagesDTOService.findById(id);
        if (language.isPresent()) return ResponseEntity.ok(language.get());
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"Language not found !"));
    }

    @GetMapping("/read/iso/{iso}")
    public ResponseEntity<Object> findById(@PathVariable String iso){
        Optional<LanguagesDTORead> language = languagesDTOService.findByIso(iso);
        if (language.isPresent()) return ResponseEntity.ok(language.get());
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2("iso","Language not found !"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody LanguagesDTOCreate languageDTOCreate) {
        Optional<LanguagesDTORead> language = languagesDTOService.update(id, languageDTOCreate);
        if (language.isPresent()) return ResponseEntity.ok(language.get());
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"Language not found !"));
    }

    @PutMapping("/update/iso/{iso}")
    public ResponseEntity<Object> update(@PathVariable String iso, @RequestBody LanguagesDTOCreate languageDTOCreate) {
        Optional<LanguagesDTORead> language = languagesDTOService.update(iso, languageDTOCreate);
        if (language.isPresent()) return ResponseEntity.ok(language.get());
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2("iso","Language not found !"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        if (languagesDTOService.deleteById(id)) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseBody("Language successfully deleted !"));
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"Language not found !"));
    }

    @DeleteMapping("/delete/iso/{iso}")
    public ResponseEntity<Object> delete(@PathVariable String iso){
        if (languagesDTOService.deleteByIso(iso)) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseBody("Language successfully deleted !"));
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2("iso","Language not found !"));
    }
}

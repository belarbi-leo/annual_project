package ecodeli.DTOController;

import ecodeli.DTO.create.SubscriptionsDTOCreate;
import ecodeli.DTO.read.SubscriptionsDTORead;
import ecodeli.DTOService.SubscriptionsDTOService;
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
@RequestMapping("/subscriptions${api.suffixe}")
@AllArgsConstructor
public class SubscriptionsDTOController {

    private final SubscriptionsDTOService subscriptionsDTOService;
    private final String nameId = "idSubscription";

    @PostMapping("/create")
    public ResponseEntity<SubscriptionsDTORead> create(@Valid @RequestBody SubscriptionsDTOCreate subscriptionDTOCreate){
        SubscriptionsDTORead subscriptionDTORead = subscriptionsDTOService.create(subscriptionDTOCreate);
        URI location = URI.create("/subscriptions/" + (subscriptionDTORead).getIdSubscription());
        return ResponseEntity.created(location).body(subscriptionDTORead);
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
        if ("none".equals(paging)) return ResponseEntity.ok(subscriptionsDTOService.readAll(sorting));
        else return ResponseEntity.ok(subscriptionsDTOService.readAll(PageRequest.of((page!=null && page>=0)?page:0, (size!=null && size >0)?size:10, sorting)));
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id){
        Optional<SubscriptionsDTORead> subscription = subscriptionsDTOService.findById(id);
        if (subscription.isPresent()) return ResponseEntity.ok(subscription.get());
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"Subscription not found !"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody SubscriptionsDTOCreate subscriptionDTOCreate) {
        Optional<SubscriptionsDTORead> subscription = subscriptionsDTOService.update(id, subscriptionDTOCreate);
        if (subscription.isPresent()) return ResponseEntity.ok(subscription.get());
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"Subscription not found !"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id){
        if (subscriptionsDTOService.deleteById(id)) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseBody("Subscription successfully deleted !"));
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"Subscription not found !"));
    }
}

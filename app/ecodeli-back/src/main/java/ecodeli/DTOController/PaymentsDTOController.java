package ecodeli.DTOController;

import ecodeli.DTO.create.PaymentsDTOCreate;
import ecodeli.DTO.read.PaymentsDTORead;
import ecodeli.DTOService.PaymentsDTOService;
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
@RequestMapping("/payments${api.suffixe}")
@AllArgsConstructor
public class PaymentsDTOController {

    private final PaymentsDTOService paymentsDTOService;
    private final String nameId = "idPayment";

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody PaymentsDTOCreate paymentDTOCreate){
        Object obj = paymentsDTOService.create(paymentDTOCreate);
        if (obj instanceof PaymentsDTORead){
            URI location = URI.create("/payments/" + ((PaymentsDTORead)obj).getIdPayment());
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
        if ("none".equals(paging)) return ResponseEntity.ok(paymentsDTOService.readAll(sorting));
        else return ResponseEntity.ok(paymentsDTOService.readAll(PageRequest.of((page!=null && page>=0)?page:0, (size!=null && size >0)?size:10, sorting)));
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id){
        Optional<PaymentsDTORead> payment = paymentsDTOService.findById(id);
        if (payment.isPresent()) return ResponseEntity.ok(payment.get());
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"Payment not found !"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody PaymentsDTOCreate paymentDTOCreate) {
        Optional<Object> payment = paymentsDTOService.update(id, paymentDTOCreate);
        if (payment.isPresent()) {
            if (payment.get() instanceof PaymentsDTORead) return ResponseEntity.ok(payment.get());
            else return ResponseEntity.badRequest().body(payment.get());
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"Payment not found !"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        if (paymentsDTOService.deleteById(id)) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseBody("Payment successfully deleted !"));
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"Payment not found !"));
    }
}

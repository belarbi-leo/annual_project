package ecodeli.DTOController;

import ecodeli.DTO.create.RoutesDTOCreate;
import ecodeli.DTO.read.RoutesDTORead;
import ecodeli.DTOService.RoutesDTOService;
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
@RequestMapping("/routes${api.suffixe}")
@AllArgsConstructor
public class RoutesDTOController {

    private final RoutesDTOService routesDTOService;
    private final String nameId = "idRoute";

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody RoutesDTOCreate routeDTOCreate){
        Object obj = routesDTOService.create(routeDTOCreate);
        if (obj instanceof RoutesDTORead){
            URI location = URI.create("/routes/" + ((RoutesDTORead)obj).getIdRoute());
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
        if ("none".equals(paging)) return ResponseEntity.ok(routesDTOService.readAll(sorting));
        else return ResponseEntity.ok(routesDTOService.readAll(PageRequest.of((page!=null && page>=0)?page:0, (size!=null && size >0)?size:10, sorting)));
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id){
        Optional<RoutesDTORead> route = routesDTOService.findById(id);
        if (route.isPresent()) return ResponseEntity.ok(route.get());
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"Route not found !"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody RoutesDTOCreate routeDTOCreate) {
        Optional<Object> route = routesDTOService.update(id, routeDTOCreate);
        if (route.isPresent()) {
            if (route.get() instanceof RoutesDTORead) return ResponseEntity.ok(route.get());
            else return ResponseEntity.badRequest().body(route.get());
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"Route not found !"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        if (routesDTOService.deleteById(id)) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseBody("Route successfully deleted !"));
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2(nameId,"Route not found !"));
    }
}

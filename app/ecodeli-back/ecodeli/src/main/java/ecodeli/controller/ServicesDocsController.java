package ecodeli.controller;

import ecodeli.modele.ServicesDocs;
import ecodeli.service.ServicesDocsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Profile("dev")
@RestController
@RequestMapping("/servicesDocs")
@AllArgsConstructor
public class ServicesDocsController {

    private final ServicesDocsService servicesDocsService;

    @PostMapping("/create")
    public ServicesDocs create(@RequestBody ServicesDocs serviceDoc){
        return servicesDocsService.create(serviceDoc);
    }

    @GetMapping("/read")
    public List<ServicesDocs> readAll(){
        return servicesDocsService.readAllOrderById();
        //return services_docsService.readAll();
    }

    @GetMapping("/read/{id}")
    public ServicesDocs findById(@PathVariable Integer id){
        return servicesDocsService.findById(id);
    }


    @PutMapping("/update/{id}")
    public ServicesDocs update(@PathVariable Integer id, @RequestBody ServicesDocs serviceDoc) {
        return servicesDocsService.update(id, serviceDoc);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return servicesDocsService.deleteById(id);
    }
}


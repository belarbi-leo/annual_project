package com.pa2aresgi.pa2a.controllerDTO;

import com.pa2aresgi.pa2a.DTO.create.UsersDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.UsersDTORead;
import com.pa2aresgi.pa2a.enumeratation.RoleEnum;
import com.pa2aresgi.pa2a.tools.ResponseBody;
import com.pa2aresgi.pa2a.serviceDTO.UsersServiceDTO;
import com.pa2aresgi.pa2a.tools.ResponseErrorV2;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.Optional;

@Profile({"dev", "prod"})
@RestController
@RequestMapping("/users${api.suffixe}")
@AllArgsConstructor
public class UsersDTOController {

    private final UsersServiceDTO usersServiceDTO;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody UsersDTOCreate userDTOCreate){
        Object obj = usersServiceDTO.create(userDTOCreate);
        if (obj instanceof UsersDTORead){
            URI location = URI.create("/usersDTO/" + ((UsersDTORead)obj).getIdUser());
            return ResponseEntity.created(location).body(obj);
        } else {
            return ResponseEntity.badRequest().body(obj);
        }
        /*UsersDTORead user = usersServiceDTO.create(userDTOCreate);
        URI location = URI.create("/usersDTO/" + user.getId_user());
        return ResponseEntity.created(location).body(user);*/
    }

    @GetMapping("/read")
    public ResponseEntity<Object> readAll(
            @RequestParam(name = "role", required = false) RoleEnum role,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Sort sorting = Sort.by(Sort.Direction.ASC,"idUser");
        if (sort != null) {
            if (sort.equals("desc")) sorting = Sort.by(Sort.Direction.DESC,"idUser");
            else if (sort.equals("none")) sorting = Sort.unsorted();
            //else sorting = Sort.by(Sort.Direction.ASC,"idUser");
        }
        if (role!=null) {
            if (page != null || size != null) return ResponseEntity.ok(usersServiceDTO.readAllByRole(role, PageRequest.of((page!=null && page>=0)?page:0, (size!=null && size >0)?size:10, sorting)));
            else return ResponseEntity.ok(usersServiceDTO.readAllByRole(role,sorting));
        } else {
            if (page != null || size != null) return ResponseEntity.ok(usersServiceDTO.readAll(PageRequest.of((page!=null && page>=0)?page:0, (size!=null && size >0)?size:10, sorting)));
            else return ResponseEntity.ok(usersServiceDTO.readAll(sorting));
        }
    }

    /*@GetMapping("/read")
    public ResponseEntity<List<UsersDTORead>> readAll(@RequestParam(name = "role", required = false) RoleEnum role){
        if (role!=null) return ResponseEntity.ok(usersServiceDTO.readAllByRole(role, Sort.by(Sort.Direction.ASC, "idUser")));
        return ResponseEntity.ok(usersServiceDTO.readAll(Sort.by(Sort.Direction.ASC, "idUser")));
    }*/

    @GetMapping("/read/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id){
        Optional<UsersDTORead> user = usersServiceDTO.findById(id);
        if (user.isPresent()) return ResponseEntity.ok(user.get());
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2("idUser","User not found !"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody UsersDTOCreate userDTOCreate) {
        Optional<Object> user = usersServiceDTO.update(id, userDTOCreate);
        if (user.isPresent()) {
            if (user.get() instanceof UsersDTORead) return ResponseEntity.ok(user.get());
            else return ResponseEntity.badRequest().body(user.get());
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2("idUser","User not found !"));
        //else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseError("User not found !"));
        //return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        //return usersServiceDTO.deleteById(id) ? ResponseEntity.noContent("User deleted successfully !").build() : ResponseEntity.notFound().build();
        if (usersServiceDTO.deleteById(id)) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseBody("User successfully deleted !"));
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2("idUser","User not found !"));
    }
}

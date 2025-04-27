package com.pa2aresgi.pa2a.controller;

import com.pa2aresgi.pa2a.modele.Languages;
import com.pa2aresgi.pa2a.service.LanguagesService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Profile({"dev", "prod"})
@RestController
@RequestMapping("/languages")
@AllArgsConstructor
public class LanguagesController {

    private final LanguagesService languagesService;

    @PostMapping("/create")
    public Languages create(@RequestBody Languages language){
        return languagesService.create(language);
    }

    @GetMapping("/read")
    public List<Languages> readAll(@RequestParam(required = false) String sort){
        if (sort != null) return languagesService.readAll(Sort.by(sort.equals("desc")?Sort.Direction.DESC:Sort.Direction.ASC,"idLanguage"));
        //return languagesService.readAllOrderById();
        return languagesService.readAll();
    }

    @GetMapping("/readPage")
    public Slice<Languages> readAll(@RequestParam(required = false) String sort, @RequestParam(required = true) Integer page, @RequestParam(required = true) Integer size){
        Sort.Direction sorting = null;
        if (sort != null){
            if (sort.equals("desc")) sorting = Sort.Direction.DESC;
            else if (sort.equals("none")) {}
            else sorting = Sort.Direction.ASC;
        }
        page = (page != null && page >= 0)?page:0;
        size = (size != null && size > 0)?size:10;
        if (sorting == null) return languagesService.readAll(PageRequest.of(page,size));
        else return languagesService.readAll(PageRequest.of(page,size,Sort.by(sorting,"idLanguage")));

    }

    /*{
        Sort sorting = null;
        if (sort != null){
            if (sort.equals("desc")) sorting = Sort.Direction.DESC;
            else if (sort.equals("none")) sorting = Sort.unsorted();
            else sorting = Sort.Direction.ASC;
            if (page == null && size == null)
            return languagesService.readAll(sorting);
        }
        if ((page != null && page >= 0) || (size != null && size > 0)) {
            if (sorting == null)
            return languagesService.readAll(PageRequest.of(
                (page != null && page >= 0)?page:0,
                (size != null && size > 0)?size:10);
            else return languagesService.readAll(PageRequest.of(
                (page != null && page >= 0)?page:0,
                (size != null && size > 0)?size:10,
                sorting);
        } else return languagesServices.readAll();
    }*/

    @GetMapping("/read/{id}")
    public Languages findById(@PathVariable Integer id){
        return languagesService.findById(id);
    }


    @PutMapping("/update/{id}")
    public Languages update(@PathVariable Integer id, @RequestBody Languages language) {
        return languagesService.update(id, language);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return languagesService.deleteById(id);
    }
}

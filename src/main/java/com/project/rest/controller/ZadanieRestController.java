package com.project.rest.controller;
import com.project.rest.model.Student;
import com.project.rest.model.Zadanie;
import com.project.rest.service.StudentService;
import com.project.rest.service.ZadanieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class ZadanieRestController {

    private ZadanieService zadanieService;

    @Autowired
    public ZadanieRestController(ZadanieService zadanieService){
        this.zadanieService = zadanieService;
    }

    @GetMapping("/zadania/{zadanieID}")
    ResponseEntity<Zadanie> getProjekt(@PathVariable Integer zadanieID){
        return ResponseEntity.of(zadanieService.getZadanie(zadanieID));
    }

    @PostMapping(path = "/zadania")
    ResponseEntity<Void> createZadanie(@Valid @RequestBody Zadanie zadanie ){

        Zadanie createZadanie = zadanieService.setZadanie(zadanie);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{zadanieID}").buildAndExpand(createZadanie.getZadanieId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/zadania/{zadanieID}")
    public ResponseEntity<Void> updateZadanie(@Valid @RequestBody Zadanie zadanie,
                                              @PathVariable Integer zadanieID) {
        return zadanieService.getZadanie(zadanieID)
                .map(p -> {
                    zadanieService.setZadanie(zadanie);
                    return new ResponseEntity<Void>(HttpStatus.OK); // 200 (można też zwracać 204 - No content)
                })
                .orElseGet(() -> ResponseEntity.notFound().build()); // 404 - Not found
    }

    @DeleteMapping("/zadania/{zadanieID}")
    public ResponseEntity<Void> deletZadanie(@PathVariable Integer zadanieID) {
        return zadanieService.getZadanie(zadanieID).map(p -> {
            zadanieService.deleteZadanie(zadanieID);
            return new ResponseEntity<Void>(HttpStatus.OK); // 200
        }).orElseGet(() -> ResponseEntity.notFound().build()); // 404 - Not found
    }

    @GetMapping(value = "/zadania")
    Page<Zadanie> getZadania(Pageable pageable) { // @RequestHeader HttpHeaders headers – jeżeli potrzebny
        return zadanieService.getZadanie(pageable); // byłby nagłówek, wystarczy dodać drugą zmienną z adnotacją
    }

//    @GetMapping(value = "/zadania", params="nazwa")
//    Page<Zadanie> getStudentByImime(@RequestParam String nazwa, Pageable pageable) {
//        return zadanieService.searchByNazwa(nazwa, pageable);
//    }
}

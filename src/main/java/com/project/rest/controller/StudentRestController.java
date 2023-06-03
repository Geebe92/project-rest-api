package com.project.rest.controller;

import com.project.rest.model.Student;
import com.project.rest.service.StudentService;
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
public class StudentRestController {

    private StudentService studentService;

    @Autowired
    public StudentRestController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping("/student/{studentID}")
    ResponseEntity<Student> getProjekt(@PathVariable Integer studentID){
        return ResponseEntity.of(studentService.getStudent(studentID));
    }

    @PostMapping(path = "/studenci")
    ResponseEntity<Void> createStudent(@Valid @RequestBody Student student ){

        Student createStudent = studentService.setStudent(student);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{studentId}").buildAndExpand(createStudent.getStudentId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/studenci/{studentId}")
    public ResponseEntity<Void> updateStudent(@Valid @RequestBody Student student,
                                              @PathVariable Integer studentId) {
        return studentService.getStudent(studentId)
                .map(p -> {
                    studentService.setStudent(student);
                    return new ResponseEntity<Void>(HttpStatus.OK); // 200 (można też zwracać 204 - No content)
                })
                .orElseGet(() -> ResponseEntity.notFound().build()); // 404 - Not found
    }

    @DeleteMapping("/studenci/{studentId}")
    public ResponseEntity<Void> deletStudent(@PathVariable Integer studentId) {
        return studentService.getStudent(studentId).map(p -> {
            studentService.deleteStudent(studentId);
            return new ResponseEntity<Void>(HttpStatus.OK); // 200
        }).orElseGet(() -> ResponseEntity.notFound().build()); // 404 - Not found
    }

    @GetMapping(value = "/studenci")
    Page<Student> getStudenci(Pageable pageable) { // @RequestHeader HttpHeaders headers – jeżeli potrzebny
        return studentService.getStudent(pageable); // byłby nagłówek, wystarczy dodać drugą zmienną z adnotacją
    }

    @GetMapping(value = "/studenci", params="nazwa")
    Page<Student> getStudentByImime(@RequestParam String nazwa, Pageable pageable) {
        return studentService.searchByImie(nazwa, pageable);
    }
}
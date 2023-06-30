package com.example.controller;

import com.example.entity.Person;
import com.example.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class Controller {
    private final PersonService service;

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @GetMapping("email/{email}")
    public ResponseEntity<Person> getPersonById(@PathVariable(name = "email") String email) {
        return new ResponseEntity<>(service.getByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Person>> getPersons() {
        return new ResponseEntity<>(service.getAllPersons(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        return new ResponseEntity<>(service.createPerson(person), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
        return new ResponseEntity<>(service.updatePerson(person), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> removePerson(@PathVariable Long id) {
        return new ResponseEntity<>(service.removePerson(id) ?
                "Пользователь удален" : "Удаление не удачно", HttpStatus.OK);
    }

    @PutMapping("/change/role/")
    public ResponseEntity<Person> updatePerson(@PathVariable String role, @PathVariable Long id) {
        return new ResponseEntity<>(service.changeRole(role, id), HttpStatus.OK);
    }
}



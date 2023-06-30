package com.example.service;

import com.example.entity.Person;
import com.example.entity.Role;
import com.example.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository repository;

    public List<Person> getAllPersons() {
        return repository.findAll();
    }

    public Person getById(Long id) {
        Optional<Person> optionalPerson = repository.findById(id);
        return optionalPerson.orElse(null);
    }

    public Person createPerson(Person person) {
        return repository.save(person);
    }

    public Person updatePerson(Person person) {
        Optional<Person> optionalPerson = repository.findById(person.getId());
        if (optionalPerson.isPresent()) {
            return repository.save(person);
        }
        return null;
    }

    public boolean removePerson(Long id) {
        Optional<Person> optionalPerson = repository.findById(id);
        if (optionalPerson.isPresent()) {
            repository.delete(optionalPerson.get());
            return true;
        }
        return false;
    }

    public Person changeRole(String role, Long id) {
        Optional<Person> optionalPerson = repository.findById(id);
        if (optionalPerson.isPresent()) {
            optionalPerson.get().setRole(Role.valueOf(role));
            return repository.save(optionalPerson.get());
        }
        return null;
    }

    public Person getByEmail(String email) {
        Optional<Person> byEmail = repository.findByEmail(email);
        if (byEmail.isPresent()) {
            log.info("{}", byEmail.get());
            return byEmail.get();
        }
        return null;
    }
}

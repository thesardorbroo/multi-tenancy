package uz.sardorbroo.multitenancy.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.sardorbroo.multitenancy.domain.common.Person;
import uz.sardorbroo.multitenancy.repository.common.PersonRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PersonResource {

    private final PersonRepository repository;

    @PostMapping("/person")
    public ResponseEntity<Person> create(@RequestBody Person person) {
        Person savedPerson = repository.save(person);
        return ResponseEntity.ok(savedPerson);
    }

    @PutMapping("/person")
    public ResponseEntity<Person> update(@RequestBody Person person) {
        Person savedPerson = repository.save(person);
        return ResponseEntity.ok(savedPerson);
    }

    @GetMapping("/person")
    public ResponseEntity<List<Person>> getAll() {
        List<Person> persons = repository.findAll();
        return ResponseEntity.ok(persons);
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<Person> getById(@PathVariable Long id) {
        Optional<Person> person = repository.findById(id);
        return ResponseEntity.ok(person.orElse(null));
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<Person> delete(@PathVariable Long id) {
        Optional<Person> person = repository.findById(id);
        repository.deleteById(id);
        return ResponseEntity.ok(person.orElse(null));
    }
}

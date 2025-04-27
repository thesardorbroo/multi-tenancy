package uz.sardorbroo.multitenancy.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.sardorbroo.multitenancy.domain.common.Person;
import uz.sardorbroo.multitenancy.domain.tenancy.Fruit;
import uz.sardorbroo.multitenancy.repository.common.PersonRepository;
import uz.sardorbroo.multitenancy.repository.tenancy.FruitRepository;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PersonAndFruitResource {

    private final PersonRepository personRepository;
    private final FruitRepository fruitRepository;

    @GetMapping("/person-and-fruit")
    public ResponseEntity<Map<Object, Object>> getPersonAndFruit() {

        List<Person> persons = personRepository.findAll();
        List<Fruit> fruits = fruitRepository.findAll();

        return ResponseEntity.ok(Map.of("persons", persons, "fruits", fruits));
    }
}

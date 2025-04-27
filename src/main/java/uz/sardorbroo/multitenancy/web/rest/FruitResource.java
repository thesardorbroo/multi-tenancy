package uz.sardorbroo.multitenancy.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.sardorbroo.multitenancy.domain.tenancy.Fruit;
import uz.sardorbroo.multitenancy.repository.tenancy.FruitRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FruitResource {

    private final FruitRepository repository;

    @PostMapping("/fruit")
    public ResponseEntity<Fruit> create(@RequestBody Fruit fruit) {
        Fruit savedFruit = repository.save(fruit);
        return ResponseEntity.ok(savedFruit);
    }

    @PutMapping("/fruit")
    public ResponseEntity<Fruit> update(@RequestBody Fruit fruit) {
        Fruit updatedFruit = repository.save(fruit);
        return ResponseEntity.ok(updatedFruit);
    }

    @GetMapping("/fruit")
    public ResponseEntity<List<Fruit>> getAll() {
        List<Fruit> fruits = repository.findAll();
        return ResponseEntity.ok(fruits);
    }

    @GetMapping("/fruit/{id}")
    public ResponseEntity<Fruit> getById(@PathVariable Long id) {
        Optional<Fruit> fruit = repository.findById(id);
        return ResponseEntity.ok(fruit.orElse(null));
    }

    @DeleteMapping("/fruit/{id}")
    public ResponseEntity<Fruit> delete(@PathVariable Long id) {
        Optional<Fruit> fruit = repository.findById(id);
        repository.deleteById(id);
        return ResponseEntity.ok(fruit.orElse(null));
    }

}

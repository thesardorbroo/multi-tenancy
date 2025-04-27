package uz.sardorbroo.multitenancy.repository.tenancy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.sardorbroo.multitenancy.domain.tenancy.Fruit;

@Repository
public interface FruitRepository extends JpaRepository<Fruit, Long> {
}

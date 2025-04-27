package uz.sardorbroo.multitenancy.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.sardorbroo.multitenancy.domain.common.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}

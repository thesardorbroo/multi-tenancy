package uz.sardorbroo.multitenancy.domain.tenancy;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "fruit")
public class Fruit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

}

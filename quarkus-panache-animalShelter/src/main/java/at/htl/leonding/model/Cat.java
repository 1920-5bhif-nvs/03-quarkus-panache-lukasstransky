package at.htl.leonding.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Cat extends Pet{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //region Constructors
    public Cat() {
    }

    public Cat(String breed, int age, double weight, String name, double price) {
        super(breed, age, weight, name, price);
    }
    //endregion constructors

    @Override
    public Long getId() {
        return id;
    }
}

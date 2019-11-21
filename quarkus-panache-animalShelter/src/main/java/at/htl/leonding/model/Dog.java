package at.htl.leonding.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Dog extends Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //region Constructors
    public Dog() {
    }

    public Dog(String breed, int age, double weight, String name, double price) {
        super(breed, age, weight, name, price);
    }
    //endregion

    @Override
    public Long getId() {
        return id;
    }
}
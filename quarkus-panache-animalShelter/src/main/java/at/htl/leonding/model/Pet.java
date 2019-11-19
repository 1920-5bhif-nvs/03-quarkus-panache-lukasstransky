package at.htl.leonding.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name = "Pet.findAll", query = "select a from Pet a")
public abstract class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String breed; //Rasse
    private int age;
    private double weight;
    private String name;
    private double price;

    //region constructors
    public Pet() {
    }

    public Pet(String breed, int age, double weight, String name, double price) {
        this.breed = breed;
        this.age = age;
        this.weight = weight;
        this.name = name;
        this.price = price;
    }
    //endregion

    //region getter and setter
    public Long getId() {
        return id;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    //endregion
}

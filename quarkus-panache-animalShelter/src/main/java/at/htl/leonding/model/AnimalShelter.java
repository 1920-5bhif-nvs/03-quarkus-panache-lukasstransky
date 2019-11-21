package at.htl.leonding.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Entity
@XmlRootElement
public class AnimalShelter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //identity beginnt bei jeder Tabelle bei 0
    private Long id;

    private String town;
    private String street;
    private int post_code;

    //region Constructors
    public AnimalShelter() {

    }

    public AnimalShelter(String town, String street, int post_code) {
        this.setTown(town);
        this.setStreet(street);
        this.setPost_code(post_code);
    }
    //endregion

    //region Getter And Setter
    public Long getId() {
        return id;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getPost_code() {
        return post_code;
    }

    public void setPost_code(int post_code) {
        this.post_code = post_code;
    }
    //endregion
}
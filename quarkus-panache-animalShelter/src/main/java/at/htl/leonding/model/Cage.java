package at.htl.leonding.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Entity
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Cage.findAll", query = "select c from Cage c"),
        @NamedQuery(name = "Cage.findByRow", query = "select c from Cage c where c.cage_row = :row"),
        @NamedQuery(name = "Cage.findByColumn", query = "select c from Cage c where c.cage_column = :column"),
})
public class Cage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int cage_row;
    private int cage_column;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cage_id") //um assoziative Tabelle zu vermeiden
    private List<Pet> pets = new ArrayList<>();

    //region Constructors
    public Cage() {
    }

    public Cage(int cage_row, int cage_column) {
        this.setCage_row(cage_row);
        this.setCage_column(cage_column);
    }
    //endregion

    //region Getter And Setter
    public Long getId() { return id; }

    public int getCage_row() {
        return cage_row;
    }

    public void setCage_row(int cage_row) {
        this.cage_row = cage_row;
    }

    public int getCage_column() {
        return cage_column;
    }

    public void setCage_column(int cage_column) {
        this.cage_column = cage_column;
    }

    public List<Pet> getPets() {
        return pets;
    }
    //endregion

    //region methods
    public void addPet(Pet pet){
        if(!this.pets.contains(pet))
            this.pets.add(pet);
    }

    public void removePet(Pet pet){
        if(this.pets.contains(pet))
            this.pets.remove(pet);
    }
    //endregion
}
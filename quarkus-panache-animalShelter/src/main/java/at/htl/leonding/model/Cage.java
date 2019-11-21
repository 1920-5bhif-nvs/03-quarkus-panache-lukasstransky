package at.htl.leonding.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Cage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int cageRow;
    private int cageColumn;

    @ManyToOne(cascade = CascadeType.ALL)
    private AnimalShelter animalShelter;

    //region Constructors
    public Cage() {
    }

    public Cage(int cageRow, int cageColumn) {
        this.setCageRow(cageRow);
        this.setCageColumn(cageColumn);
    }
    //endregion

    //region Getter And Setter
    public Long getId() { return id; }

    public int getCageRow() {
        return cageRow;
    }

    public void setCageRow(int cageRow) {
        this.cageRow = cageRow;
    }

    public int getCageColumn() {
        return cageColumn;
    }

    public void setCageColumn(int cageColumn) {
        this.cageColumn = cageColumn;
    }
    //endregion

    public AnimalShelter getAnimalShelter() {
        return animalShelter;
    }

    public void setAnimalShelter(AnimalShelter animalShelter) {
        this.animalShelter = animalShelter;
    }
}
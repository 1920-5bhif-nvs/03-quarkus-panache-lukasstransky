package at.htl.leonding.business;

import at.htl.leonding.model.Cage;
import at.htl.leonding.model.Dog;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityGraph;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class CagePanacheRepository implements PanacheRepository<Cage> {

    @Transactional
    public Cage save(Cage cage){
        this.persistAndFlush(cage);
        return this.findById(cage.getId());
    }

    @Transactional
    public Cage update(Cage cage){
        this.save(cage);
        return this.findById(cage.getId());
    }

    public List<Cage> findCagesWithRowMinMax(int rowmin, int rowmax){
        return this.find("row >= ?1 and row <= ?2", rowmin, rowmax).list();
    }

    public List<Cage> findAllCagesForSpecificAnimalShelter(Long id){
        return this.find("animalshelter_id = ?1", id).list();
    }
}

package at.htl.leonding.business;

import at.htl.leonding.model.Cage;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

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
        return this.find("cage_row >= ?1 and cage_row <= ?2", rowmin, rowmax).list();
    }

    public List<Cage> findCagesWithColumnMinMax(int columnmin, int columnmax){
        return this.find("cage_column >= ?1 and cage_column <= ?2", columnmin, columnmax).list();
    }

    public List<Cage> findCagesWithRowColumn(int row, int column){
        return this.find("cage_row = ?1 and cage_column = ?2", row, column).list();
    }
}

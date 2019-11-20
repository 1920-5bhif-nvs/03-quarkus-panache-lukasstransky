package at.htl.leonding.business;

import at.htl.leonding.model.AnimalShelter;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class AnimalShelterPanacheRepository implements PanacheRepository<AnimalShelter> {

    @Inject
    CagePanacheRepository cagePanacheRepository;

    @Transactional
    public AnimalShelter save(AnimalShelter animalShelter){
        this.persistAndFlush(animalShelter);
        return this.findById(animalShelter.getId());
    }

    @Transactional
    public AnimalShelter update(AnimalShelter animalShelter){
        this.save(animalShelter);
        return this.findById(animalShelter.getId());
    }

    public AnimalShelter findByTown(String town){
        return find("town", town).firstResult();
    }
}

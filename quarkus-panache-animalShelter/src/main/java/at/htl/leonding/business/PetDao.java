package at.htl.leonding.business;

import at.htl.leonding.model.Pet;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class PetDao {

    @PersistenceContext
    EntityManager em;

    public List<Pet> getAllPets(){
        return em.createNamedQuery("Pet.findAll").getResultList();
    }

    public List<Pet> getAllPetsByDiscriminator(String animal){
        return em.createNamedQuery("Pet.getAllPetsByDiscriminator").setParameter("dtype", animal).getResultList();
    }

    public List<Pet> getAllPetsFromCage(Long id){
        String sql = "select * from pet where cage_id = ?1";
        Query query = em.createNativeQuery(sql, Pet.class).setParameter(1, id);
        return query.getResultList();
    }
}

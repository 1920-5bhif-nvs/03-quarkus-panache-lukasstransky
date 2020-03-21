package at.htl.leonding.business;

import at.htl.leonding.model.Cage;
import at.htl.leonding.model.Dog;
import at.htl.leonding.model.Pet;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Pet testEntityGraph(){
        EntityGraph graph = em.getEntityGraph("GraphCage");
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.fetchgraph", graph);
        return em.find(Pet.class, 1L, properties);
    }
}

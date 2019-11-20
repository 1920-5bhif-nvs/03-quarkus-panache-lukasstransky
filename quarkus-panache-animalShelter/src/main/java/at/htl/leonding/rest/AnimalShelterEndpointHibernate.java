package at.htl.leonding.rest;

import at.htl.leonding.model.AnimalShelter;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("animalShelter/hibernate")
@Produces(MediaType.APPLICATION_JSON)
public class AnimalShelterEndpointHibernate {

    @Inject
    EntityManager em;

    @GET
    public Response getAllAnimalShelters(){
        TypedQuery<AnimalShelter> query = em.createNamedQuery("AnimalShelter.findAll", AnimalShelter.class);
        List<AnimalShelter> shelters = query.getResultList();
        return Response.ok().entity(shelters).build();
    }

    @GET
    @Path("count")
    public int count(){
        return em.createNamedQuery("AnimalShelter.findAll", AnimalShelter.class).getResultList().size();
    }

    @GET
    @Path("{id}")
    public AnimalShelter getById(@PathParam("id") long id) {
        return em.find(AnimalShelter.class, id);
    }

    @GET
    @Path("/town/{town}")
    public AnimalShelter getByTown(@PathParam("town") String town) {
        return em.createNamedQuery("AnimalShelter.findByTown", AnimalShelter.class).setParameter("town", town).getSingleResult();
    }

    @POST
    public Long putAnimalShelter(AnimalShelter animalShelter) {
        em.persist(animalShelter);
        return animalShelter.getId();
    }

    @DELETE
    public void deleteAnimalShelter(@QueryParam("id") long id) {
        AnimalShelter a = em.find(AnimalShelter.class, id);
        em.remove(a);
    }

}
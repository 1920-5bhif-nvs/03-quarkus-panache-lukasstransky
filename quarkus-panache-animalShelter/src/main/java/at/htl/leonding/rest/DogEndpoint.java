package at.htl.leonding.rest;

import at.htl.leonding.model.Dog;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("dog")
public class DogEndpoint {

    @Inject
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDogs(){
        TypedQuery<Dog> query = em.createNamedQuery("Dog.findAll", Dog.class);
        List<Dog> result = query.getResultList();
        return Response.ok().entity(result).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getDogById(@PathParam("id") long id) {
        Dog dog = em.find(Dog.class, id);
        return Response.ok().entity(dog).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/name/{name}")
    public Response getDogsByName(@PathParam("name") String name) {
        TypedQuery<Dog> query = em.createNamedQuery("Dog.findByName", Dog.class);
        query.setParameter("name", name);
        List<Dog> result = query.getResultList();
        return Response.ok().entity(result).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/age/{age}")
    public Response getDogsByAge(@PathParam("age") int age) {
        TypedQuery<Dog> query = em.createNamedQuery("Dog.findByAge", Dog.class);
        query.setParameter("age", age);
        List<Dog> result = query.getResultList();
        return Response.ok().entity(result).build();
    }

    @POST
    public Long putDog(Dog dog) {
        em.persist(dog);
        return dog.getId();
    }

    @DELETE
    @Path("{id}")
    public void deleteDog(@PathParam("id") long id) {
        Dog d = em.find(Dog.class, id);
        if(d != null) {
            em.remove(em.contains(d) ? d : em.merge(d));
        }
    }
}

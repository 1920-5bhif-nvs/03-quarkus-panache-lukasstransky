package at.htl.leonding.rest;

import at.htl.leonding.model.Cat;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("cat")
public class CatEndpoint {

    @Inject
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCats(){
        TypedQuery<Cat> query = em.createNamedQuery("Cat.findAll", Cat.class);
        List<Cat> result = query.getResultList();
        return Response.ok().entity(result).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getCat(@PathParam("id") long id) {
        Cat cat = em.find(Cat.class, id);
        return Response.ok().entity(cat).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/name/{name}")
    public Response getCatsByName(@PathParam("name") String name) {
        TypedQuery<Cat> query = em.createNamedQuery("Cat.findByName", Cat.class);
        query.setParameter("name", name);
        List<Cat> result = query.getResultList();
        return Response.ok().entity(result).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/age/{age}")
    public Response getCatsByAge(@PathParam("age") int age) {
        TypedQuery<Cat> query = em.createNamedQuery("Cat.findByAge", Cat.class);
        query.setParameter("age", age);
        List<Cat> result = query.getResultList();
        return Response.ok().entity(result).build();
    }

    @POST
    public Long putCat(Cat cat) {
        em.persist(cat);
        return cat.getId();
    }

    @DELETE
    @Path("{id}")
    public void deleteCat(@PathParam("id") long id) {
        Cat c = em.find(Cat.class, id);
        if(c != null) {
            em.remove(em.contains(c) ? c : em.merge(c));
        }
    }
}
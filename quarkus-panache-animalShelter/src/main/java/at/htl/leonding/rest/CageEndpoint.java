package at.htl.leonding.rest;

import at.htl.leonding.model.Cage;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("cage")
public class CageEndpoint {

    @Inject
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCages(){
        TypedQuery<Cage> query = em.createNamedQuery("Cage.findAll", Cage.class);
        List<Cage> result = query.getResultList();
        return Response.ok().entity(result).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Cage getCage(@PathParam("id") long id) {
        return em.find(Cage.class, id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/row/{row}")
    public Response getCageByRow(@PathParam("row") int row) {
        TypedQuery<Cage> query = em.createNamedQuery("Cage.findByRow", Cage.class);
        query.setParameter("row", row);
        List<Cage> result = query.getResultList();
        return Response.ok().entity(result).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/column/{column}")
    public Response getCageByColumn(@PathParam("column") int column) {
        TypedQuery<Cage> query = em.createNamedQuery("Cage.findByColumn", Cage.class);
        query.setParameter("column", column);
        List<Cage> result = query.getResultList();
        return Response.ok().entity(result).build();
    }

    @POST
    public Long putCage(Cage cage) {
        em.persist(cage);
        return cage.getId();
    }

    @DELETE
    @Path("{id}")
    public void deleteCage(@PathParam("id") long id) {
        Cage c = em.find(Cage.class, id);
        if(c != null) {
            em.remove(em.contains(c) ? c : em.merge(c));
        }
    }

}

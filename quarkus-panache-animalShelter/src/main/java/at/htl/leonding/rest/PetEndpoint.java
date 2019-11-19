package at.htl.leonding.rest;

import at.htl.leonding.model.Pet;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("pets")
public class PetEndpoint {

    @Inject
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPets(){
        TypedQuery<Pet> query = em.createNamedQuery("Pet.findAll", Pet.class);
        List<Pet> shelters = query.getResultList();
        return Response.ok().entity(shelters).build();
    }
}

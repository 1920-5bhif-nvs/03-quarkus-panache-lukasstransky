package at.htl.leonding.rest;

import at.htl.leonding.business.PetDao;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("pet/hibernate")
@Produces(MediaType.APPLICATION_JSON)
public class PetEndpointHibernate {

    @Inject
    PetDao petDao;

    @GET
    public Response getAllPets(){
        return Response.ok().entity(petDao.getAllPets()).build();
    }

    @GET
    @Path("{animal}")
    public Response getAllPetsByDiscriminator(@PathParam("animal") String animal){
        return Response.ok().entity(petDao.getAllPetsByDiscriminator(animal)).build();
    }

    @GET
    @Path("cage")
    public Response findAllPetsFromCage(@QueryParam("id") Long id){
        return Response.ok().entity(petDao.getAllPetsFromCage(id)).build();
    }

    @GET
    @Path("graph")
    public Response testGraph(){
        return Response.ok().entity(petDao.testEntityGraph()).build();
    }
}

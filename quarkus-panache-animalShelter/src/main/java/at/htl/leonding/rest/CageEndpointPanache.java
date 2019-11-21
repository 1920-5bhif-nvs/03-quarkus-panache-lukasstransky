package at.htl.leonding.rest;

import at.htl.leonding.business.CagePanacheRepository;
import at.htl.leonding.model.Cage;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("cage/panache")
@Produces(MediaType.APPLICATION_JSON)
public class CageEndpointPanache {

    @Inject
    CagePanacheRepository cagePanacheRepository;

    @GET
    public Response getAllCages(){
        return Response.ok().entity(cagePanacheRepository.listAll()).build();
    }

    @GET
    @Path("count")
    public long count(){
        return cagePanacheRepository.count();
    }

    @GET
    @Path("id")
    public Response getById(@QueryParam("id") Long id){
        return Response.ok().entity(cagePanacheRepository.findById(id)).build();
    }

    @GET
    @Path("rowminmax")
    public Response getCagesWithRowMinMax(@QueryParam("min") int min, @QueryParam("max") int max){
        return Response.ok().entity(cagePanacheRepository.findCagesWithRowMinMax(min, max)).build();
    }

    @GET
    @Path("shelter")
    public Response getAllCagesForSpecificAnimalShelter(@QueryParam("id") Long id){
        return Response.ok().entity(cagePanacheRepository.findAllCagesForSpecificAnimalShelter(id)).build();
    }

    @POST
    @Transactional
    public Response save(Cage cage) {
        try{
            cage = cagePanacheRepository.save(cage);
            return Response.ok().entity(cage).build();
        }catch(Exception ex){
            return Response.serverError().build();
        }
    }

    @PUT
    @Transactional
    public Response update(@QueryParam("id") Long id, Cage cage){
        try{
            Cage toUpdate = cagePanacheRepository.findById(id);
            toUpdate.setCageColumn(cage.getCageColumn());
            toUpdate.setCageRow(cage.getCageRow());
            cage = cagePanacheRepository.update(toUpdate);
            return Response.ok().entity(cage).build();
        }catch(Exception ex){
            return Response.serverError().build();
        }
    }

    @DELETE
    @Transactional
    public Response delete(@QueryParam("id") long id) {
        try{
            Cage cage = cagePanacheRepository.findById(id);
            cagePanacheRepository.delete(cage);
            return Response.ok().entity(cage).build();
        }catch(Exception ex){
            return Response.serverError().build();
        }
    }
}

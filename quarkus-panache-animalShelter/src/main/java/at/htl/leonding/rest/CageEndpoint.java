package at.htl.leonding.rest;

import at.htl.leonding.business.CagePanacheRepository;
import at.htl.leonding.model.Cage;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("cage")
@Produces(MediaType.APPLICATION_JSON)
public class CageEndpoint {

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
    @Path("colminmax")
    public Response getCagesWithColumnMinMax(@QueryParam("min") int min, @QueryParam("max") int max){
        return Response.ok().entity(cagePanacheRepository.findCagesWithColumnMinMax(min, max)).build();
    }

    @GET
    @Path("rowcol")
    public Response getCagesWithRowColumn(@QueryParam("row") int row, @QueryParam("col") int column){
        return Response.ok().entity(cagePanacheRepository.findCagesWithRowColumn(row, column)).build();
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
            toUpdate.setCage_column(cage.getCage_column());
            toUpdate.setCage_row(cage.getCage_row());
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
            Cage animalShelter = cagePanacheRepository.findById(id);
            cagePanacheRepository.delete(animalShelter);
            return Response.ok().entity(animalShelter).build();
        }catch(Exception ex){
            return Response.serverError().build();
        }
    }
}

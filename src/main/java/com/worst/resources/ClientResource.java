package com.worst.resources;

import com.worst.dto.ClientDto;
import com.worst.error.NotFoundException;
import com.worst.service.ClientService;
import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;


@Path("/client")
@ApplicationScoped
@RunOnVirtualThread
public class ClientResource {

    @Inject
    ClientService clientService;


    @GET
    @Path("/getByEmail/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByEmail(@PathParam("email") @Valid String email) throws NotFoundException {
        return clientService.findByEmail(email);
    }



    @GET
    @Path("/getByJpa/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByIdJpa(@PathParam("id") Long id){
        return clientService.findByIdJpa(id);
    }

    @POST
    @Path("/createClient")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createClient(@RequestBody ClientDto clientDto){
        return clientService.createClient(clientDto);
    }


    @PUT
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateClient(@RequestBody ClientDto clientDto){
        return clientService.updateClient(clientDto);
    }


    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllClient(){
        return clientService.getAll();
    }


    @DELETE
    @Path("/delete/{id}")
    public Response deleteById(@PathParam("id") Long id){
        return clientService.deleteById(id);
    }

    @POST
    @Path("/createWithSearch")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createWithSearch(@RequestBody ClientDto clientDto) throws Exception {
        return clientService.createWithSearch(clientDto);
    }
}

package com.worst.resources;

import com.worst.dto.CardDto;
import com.worst.dto.UpdateSaldoDto;
import com.worst.error.FoundException;
import com.worst.error.NotFoundException;
import com.worst.service.CardsService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

@ApplicationScoped
@Path("/cards")
public class CardsResource {

    @Inject
    CardsService cardsService;


    @POST
    @Path("/createCard")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCard2(@RequestBody CardDto cardDto) throws FoundException {
        return cardsService.createCard(cardDto);
    }


    @PUT
    @Path("/refreshSaldp")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSaldo(@RequestBody UpdateSaldoDto updateSaldoDto) throws NotFoundException {
        return cardsService.updateSaldo(updateSaldoDto);
    }

    @GET
    @Path("/{numeroCard}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCardByNumeroCard(@PathParam("numeroCard") String numeroCard) throws NotFoundException {
        return cardsService.getCardByNumero(numeroCard);
    }

}

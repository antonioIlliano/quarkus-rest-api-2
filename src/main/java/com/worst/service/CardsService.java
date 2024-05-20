package com.worst.service;

import com.worst.converter.CardConverter;
import com.worst.dto.CardDto;
import com.worst.dto.UpdateSaldoDto;
import com.worst.error.FoundException;
import com.worst.error.NotFoundException;
import com.worst.panacheEntity.Card;
import com.worst.panacheRepository.CardsRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@ApplicationScoped
public class CardsService {

    @Inject
    CardsRepository cardsRepository;

    @Inject
    CardConverter cardConverter;
    @Transactional
    public Response createCard(CardDto cardDto) throws FoundException {

        Optional<Card> cardCheck = cardsRepository.findCardForUser(cardDto.getNumeroCarta(), cardDto.getClientId());

        if(cardCheck.isPresent()){
            throw new FoundException(Response.Status.FOUND.getStatusCode(), "card with "+cardDto.getNumeroCarta()+" altready exists");
        }

        Card card = cardConverter.toEntity(cardDto);

        card.persistAndFlush();


        return Response.status(Response.Status.CREATED).entity(cardConverter.toDto(card)).build();

    }

    @Transactional
    public Response updateSaldo(UpdateSaldoDto updateSaldoDto) throws NotFoundException {

       Card card = cardsRepository.findCardyNumero(updateSaldoDto.getNumeroCarta());

        card.getSaldoId().update("saldoContabile", updateSaldoDto.getNewSaldo());

        return Response.status(Response.Status.OK).build();

    }

    public Response getCardByNumero(String numeroCard) throws NotFoundException {

        Card card = cardsRepository.findCardyNumero(numeroCard);

        return Response.status(Response.Status.FOUND)
                .entity(cardConverter.toDto(card))
                .build();
    }
}

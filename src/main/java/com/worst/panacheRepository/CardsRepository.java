package com.worst.panacheRepository;

import com.speedment.jpastreamer.application.JPAStreamer;
import com.worst.error.NotFoundException;
import com.worst.panacheEntity.Card;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@ApplicationScoped
public class CardsRepository implements PanacheRepository<Card> {

    @Inject
    JPAStreamer jpaStreamer;

    public Optional<Card> findCardForUser(String numeroCarta, Long clientId){
        Optional<Card> card = jpaStreamer.stream(Card.class)
                .filter(c-> c.getClient().getClientId().equals(clientId))
                .filter(c-> c.getNumeroCarta().equals(numeroCarta))
                .findFirst();
        return card;
    }

    public Card findCardyNumero(String numeroCarta) throws NotFoundException {
        Optional<Card> card = jpaStreamer.stream(Card.class)
                .filter(c-> c.getNumeroCarta().equals(numeroCarta))
                .findFirst();

        if(card.isEmpty()){
            throw  new NotFoundException(Response.Status.NOT_FOUND.getStatusCode(), "not found card for numeroCarta: "+numeroCarta);
        }
        return card.get();
    }
}

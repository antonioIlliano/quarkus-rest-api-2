package com.worst.converter;

import com.worst.dto.CardDto;
import com.worst.panacheEntity.Card;
import com.worst.panacheEntity.Client;
import com.worst.panacheEntity.Saldo;
import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CardConverter {

    public Card toEntity(CardDto cardDto){

        Card card = new Card();

        card.setNomeCarta(cardDto.getNomeCarta());
        card.setClient(setClient(cardDto.getClientId()));
        card.setSaldoId(setSaldo(cardDto.getSaldo()));
        card.setNumeroCarta(cardDto.getNumeroCarta());

        return card;
    }

    public CardDto toDto(Card card){

        CardDto cardDto = new CardDto();

        cardDto.setNomeCarta(card.getNomeCarta());
        cardDto.setSaldo(card.getSaldoId().getSaldoContabile());
        cardDto.setClientId(card.getClient().getClientId());
        cardDto.setNumeroCarta(card.getNumeroCarta());

        return cardDto;
    }

    public List<CardDto> toDtoList(List<Card> cardList){

        List<CardDto> cardDtoList = new ArrayList<>();
        for (Card c:cardList){
            cardDtoList.add(toDto(c));
        }

        return cardDtoList;
    }
    private Saldo setSaldo(BigDecimal saldoContabile) {

        Saldo saldo = new Saldo();

        saldo.setSaldoContabile(saldoContabile);

        return saldo;

    }

    private Client setClient(Long clientId) {

        Client client = new Client();
        client.setClientId(clientId);

        return client;

    }

}

package com.worst.converter;

import com.worst.dto.ClientDto;
import com.worst.panacheEntity.Client;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
public class ClientConverter {

    public Client toEntity(ClientDto clientDto){

        Client client = new Client();

        client.setName(clientDto.getName());
        client.setSurname(clientDto.getSurname());
        client.setAge(clientDto.getAge());
        client.setEmail(clientDto.getEmail());

        return  client;

    }

    public ClientDto toDto(Client client){

        ClientDto clientDto = new ClientDto();

        clientDto.setName(client.getName());
        clientDto.setSurname(client.getSurname());
        clientDto.setAge(client.getAge());
        clientDto.setEmail(client.getEmail());

        return clientDto;
    }

    public List<ClientDto> toDtoList(List<Client> clientList){

        List<ClientDto> clientDtoList = new ArrayList<>();
        for (Client c : clientList){
            clientDtoList.add(toDto(c));
        }
        return clientDtoList;
    }

}

package com.worst.service;

import com.worst.converter.ClientConverter;
import com.worst.dto.ClientDto;
import com.worst.error.NotFoundException;
import com.worst.panacheEntity.Client;
import com.worst.panacheRepository.ClientRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ClientService {

    @Inject
    ClientRepository clientRepository;


    @Inject
    ClientConverter clientConverter;



    public Response findById(Long id) {

        Optional<Client> client = clientRepository.findByIdOptional(id);

        return client.isPresent()
                ? Response.status(Response.Status.FOUND).entity(clientConverter.toDto(client.get())).build()
                : Response.status(Response.Status.NOT_FOUND).build();

    }


    @Transactional
    public Response createClient(ClientDto clientDto) {
        try {
            Optional<Client> clientCheck = clientRepository.findByEmail(clientDto.getEmail());
            if (clientCheck.isPresent()) {
                return Response.status(Response.Status.FOUND).entity(clientDto).build();
            }

            Client client = clientConverter.toEntity(clientDto);

            clientRepository.persistAndFlush(client);

            return Response.status(Response.Status.CREATED).entity(clientConverter.toDto(client)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(clientDto).build();
        }

    }


    @Transactional
    public Response updateClient(ClientDto clientDto) {

        Optional<Client> clientCheck = clientRepository.findByEmail(clientDto.getEmail());

        if (!clientCheck.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).entity("no client found for "+clientDto.getEmail()).build();
        }

        Client client = clientCheck.get();

        client.setName(clientDto.getName());
        client.setSurname(clientDto.getSurname());
        client.setEmail(clientDto.getEmail());
        client.setAge(clientDto.getAge());

        clientRepository.persistAndFlush(client);

        return clientRepository.isPersistent(client)
                ? Response
                .status(Response.Status.OK)
                .entity(clientConverter.toDto(client))
                .build()
                : Response
                .status(Response.Status.NOT_MODIFIED)
                .entity(client)
                .build();
    }

    public Response getAll() {

        List<Client> clientiList = clientRepository.listAll();
        return Response
                .status(Response.Status.OK)
                .entity(clientConverter.toDtoList(clientiList))
                .build();
    }

    @Transactional
    public Response deleteById(Long id) {

        Optional<Client> client = clientRepository.findByIdOptional(id);
        if (!client.isPresent()){
            return Response.status(Response.Status.NOT_FOUND).entity("not found client for id "+id).build();
        }

        Client clientToDelete = client.get();

        clientToDelete.delete();

        return clientToDelete.isPersistent()
                ? Response.status(Response.Status.BAD_GATEWAY).entity(clientToDelete).build()
                : Response.status(Response.Status.OK).build();


    }

    public Response findByIdJpa(Long id) {

        Optional<Client> client = clientRepository.getByIdJpa(id);

        return client.isPresent()
                ? Response.status(Response.Status.FOUND).entity(clientConverter.toDto(client.get())).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    public Response createWithSearch(ClientDto clientDto) throws Exception{

        Client client = clientConverter.toEntity(clientDto);

        Client savedClient = clientRepository.checkIfPresentAndSave(client);

        return Response.status(Response.Status.CREATED).entity(clientConverter.toDto(savedClient)).build();

    }

    public Response findByEmail(String email) throws NotFoundException {

        Client client = clientRepository.searchByEmail(email);

        return Response.status(Response.Status.FOUND).entity(clientConverter.toDto(client)).build();

    }
}

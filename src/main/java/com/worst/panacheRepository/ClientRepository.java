package com.worst.panacheRepository;

import com.speedment.jpastreamer.application.JPAStreamer;
import com.worst.error.FoundException;
import com.worst.error.NotFoundException;
import com.worst.panacheEntity.Client;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;


import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class ClientRepository implements PanacheRepository<Client> {


    @Inject
    JPAStreamer jpaStreamer;

    public Optional<Client> findByEmail(String email) {


        return find("email", email).firstResultOptional();

    }


    public Client searchByEmail(String email) throws NotFoundException {

        Optional<Client> client = jpaStreamer.stream(Client.class)
                .filter(f-> f.getEmail().equals(email)).findFirst();

        if(!client.isPresent()) {
            throw new NotFoundException(Response.Status.NOT_FOUND.getStatusCode(), "client for email: "+email+" not found");
        }
        return client.get();
    }


    public void persistAll(List<Client> clientList){
        for (Client c: clientList){
            persist(c);
        }
    }

    public Optional<Client> getByIdJpa(Long id){
        return jpaStreamer.stream(Client.class)
                .filter(c-> c.getClientId().equals(id))
                .findFirst();
    }

    public Client checkIfPresentAndSave(Client client) throws FoundException {

        Optional<Client> clientByEmail = jpaStreamer.stream(Client.class)
                .filter(c-> c.getEmail().equals(client.getEmail()))
                .findFirst();

        if (clientByEmail.isPresent()){
            throw new FoundException(Response.Status.FOUND.getStatusCode(), "client with "+client.getEmail()+" altready exists");
        }
        persist(client);

        return client;
    }

}

package com.worst.panacheRepository;

import com.speedment.jpastreamer.application.JPAStreamer;
import com.worst.panacheEntity.Saldo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SaldoRepository implements PanacheRepository<Saldo> {

    @Inject
    JPAStreamer jpaStreamer;
}

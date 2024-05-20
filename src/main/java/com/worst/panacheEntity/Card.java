package com.worst.panacheEntity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "card")
@Data
@ToString
public class Card extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic
    private Long cardId;

    @Basic
    @Column(name = "nome_carta")
    private String nomeCarta;

    @Basic
    @Column(name = "numero_carta")
    private String numeroCarta;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "saldoId", referencedColumnName = "saldoId")
    @Basic
    @Nullable
    private Saldo saldoId;

    @ManyToOne
    @JoinColumn(name = "clientId")
    @Basic
    private Client client;
}

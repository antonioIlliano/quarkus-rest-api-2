package com.worst.panacheEntity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "saldo")
@ToString
public class Saldo extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic
    private Long saldoId;

    @Column(name = "saldo_contabile")
    @Basic
    private BigDecimal saldoContabile;

}

package com.worst.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CardDto {

    private String nomeCarta;

    private BigDecimal saldo;

    private Long clientId;

    private String numeroCarta;
}

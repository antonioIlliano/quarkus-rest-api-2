package com.worst.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateSaldoDto {

    private BigDecimal newSaldo;
    private String numeroCarta;

}

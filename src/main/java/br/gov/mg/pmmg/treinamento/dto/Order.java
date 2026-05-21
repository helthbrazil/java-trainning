package br.gov.mg.pmmg.treinamento.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Order {
    private final Long id;
    private final String status;
    private final BigDecimal amount;
}

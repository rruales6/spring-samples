package com.example.apibanking.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponseDTO {
    private Long id;
    private Date transactionDate;
    private BigDecimal transactionAmount;
    private BigDecimal balance;
    private String description;
    private String sucursal;

}

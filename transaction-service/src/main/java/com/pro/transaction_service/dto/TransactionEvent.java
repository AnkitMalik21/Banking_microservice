package com.pro.transaction_service.dto;

import com.pro.transaction_service.entity.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEvent {
    private String trasactionRef;
    private Long accountId;
    private TransactionType type;
    private Long amount;
    private LocalDateTime timestamp;
}

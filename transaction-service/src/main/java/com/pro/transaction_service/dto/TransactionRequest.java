package com.pro.transaction_service.dto;

import com.pro.transaction_service.entity.TransactionType;
import lombok.Data;

@Data
public class TransactionRequest {
    private Long accountId;
    private TransactionType type;
    private Long amount;
}

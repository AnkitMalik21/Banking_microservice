package com.pro.transaction_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false, updatable = false)
    private String transactionRef; //UUID for idempotency

    @Column(nullable = false)
    private Long accountId; // Loose coupling: Just the ID,not the entity

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private Long amount;

    private LocalDateTime timestamp;
}

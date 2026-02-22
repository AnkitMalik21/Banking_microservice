package com.pro.transaction_service.service;

import com.pro.transaction_service.dto.TransactionEvent;
import com.pro.transaction_service.entity.Transaction;
import com.pro.transaction_service.entity.TransactionType;
import com.pro.transaction_service.feign.AccountFeignClient;
import com.pro.transaction_service.kafka.TransactionEventProducer;
import com.pro.transaction_service.repository.TransactionRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository repo;
    private final AccountFeignClient client;
    private final TransactionEventProducer eventProducer;

    //=======credit==========
    @Transactional
    public Transaction creditTransaction(Long accountId, Long amount){
        //Call Amount service (positive amount)
        client.creditMoney(accountId,amount);

        //SAVE TRANSACTION
        Transaction savedRecord = saveTransaction(
          accountId,TransactionType.CREDIT,amount
        );

        //publish Event
        TransactionEvent event = buildEvent(savedRecord);
        eventProducer.publishTransactionEvent(event);

        return savedRecord;
    }

    //========== Debit ==============
    @Transactional
    public Transaction debitTransaction(Long accountId,Long amount){
        // Call Account Service (negative amount)
        client.debitMoney(accountId,amount);

        // Save Transaction
        Transaction savedRecord = saveTransaction(accountId,TransactionType.DEBIT,amount);

        //publish Event
        TransactionEvent event = buildEvent(savedRecord);
        eventProducer.publishTransactionEvent(event);

        return savedRecord;
    }

    //Common save method
    public Transaction saveTransaction(Long accountId,TransactionType type,Long amount){
        Transaction record = new Transaction();
        record.setTransactionRef(UUID.randomUUID().toString());
        record.setAccountId(accountId);
        record.setType(type);
        record.setAmount(amount);
        record.setTimestamp(LocalDateTime.now());

        return repo.save(record);
    }

    // common event builder
    private TransactionEvent buildEvent(Transaction record){
        return new TransactionEvent(
                record.getTransactionRef(),
                record.getAccountId(),
                record.getType(),
                record.getAmount(),
                record.getTimestamp()
        );
    }
}

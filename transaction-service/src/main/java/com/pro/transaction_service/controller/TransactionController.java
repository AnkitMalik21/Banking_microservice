package com.pro.transaction_service.controller;

import com.pro.transaction_service.dto.TransactionRequest;
import com.pro.transaction_service.entity.Transaction;
import com.pro.transaction_service.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;

    //=========== credit ===========
    @PostMapping("/credit")
    public ResponseEntity<Transaction> creditTransaction(@RequestBody TransactionRequest request){
        Transaction record = service.creditTransaction(
                request.getAccountId(),
                request.getAmount()
        );

        return new ResponseEntity<>(record, HttpStatus.OK);
    }

    //========= debit ===========
    @PostMapping("/debit")
    public ResponseEntity<Transaction> debitTransaction(@RequestBody TransactionRequest request){
        Transaction record = service.debitTransaction(
                request.getAccountId(),
                request.getAmount()
        );

        return new ResponseEntity<>(record,HttpStatus.OK);
    }
}

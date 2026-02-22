package com.pro.account_service.Controller;

import com.pro.account_service.dto.AccountRequest;
import com.pro.account_service.dto.AccountResponse;
import com.pro.account_service.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @PostMapping
    public ResponseEntity<String> createAccount(@RequestBody AccountRequest account){
        return new ResponseEntity<>(service.createAccount(account), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccountById(@PathVariable Long id){
        AccountResponse result = service.getAccount(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PutMapping("/{id}/credit-money/{amount}")
    public ResponseEntity<AccountResponse> creditMoney(@PathVariable Long id,@PathVariable Long amount){
        AccountResponse response = service.creditMoney(id,amount);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/{id}/debit-money/{amount}")
    public ResponseEntity<AccountResponse> debitMoney(@PathVariable Long id,@PathVariable Long amount){
        AccountResponse response = service.debitResponse(id,amount);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}

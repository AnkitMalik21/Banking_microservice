package com.pro.account_service.service;

import com.pro.account_service.dto.AccountRequest;
import com.pro.account_service.dto.AccountResponse;
import com.pro.account_service.entity.Account;
import com.pro.account_service.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repo;

    public String createAccount (AccountRequest accountRequest){
        Account account = new Account();
        account.setAccountHolderName(accountRequest.getName());
        account.setBalance(accountRequest.getMoney());
        repo.save(account);

        return "Account created for the user named : " + accountRequest.getName();
    }

    public AccountResponse getAccount(Long id){
        Account account = repo.findById(id)
                .orElseThrow(()-> new RuntimeException("Did not the account with the id mentioned"));
        return mapToDto(account);

    }

    public AccountResponse creditMoney(Long id, Long amount){
        Account account = repo.findById(id)
                .orElseThrow(()-> new RuntimeException("Did not found the account with the mentioned account id"));

        Long balance = account.getBalance();

        account.setBalance(balance + amount);

        repo.save(account);
        return mapToDto(account);
    }

    public AccountResponse debitResponse(Long id, Long amount){
        Account account = repo.findById(id)
                .orElseThrow(()-> new RuntimeException("Did not found the account with the mentioned id"));

        Long balance = account.getBalance();
        if(balance<amount){
            throw new RuntimeException("The amount of balance is less than withdrawal amount");
        }

        account.setBalance(balance - amount);
        repo.save(account);
        return mapToDto(account);
    }

    public AccountResponse mapToDto(Account account){
        AccountResponse ac = new AccountResponse();
        ac.setId(account.getId());
        ac.setAccountHolderName(account.getAccountHolderName());
        ac.setBalance(account.getBalance());
        return ac;
    }
}

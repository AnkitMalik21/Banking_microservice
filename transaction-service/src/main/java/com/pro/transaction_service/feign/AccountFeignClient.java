package com.pro.transaction_service.feign;

import com.pro.transaction_service.dto.AccountResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "api-gateway")
public interface AccountFeignClient {

    @PutMapping("/api/accounts/{id}/credit-money/{amount}")
    AccountResponse creditMoney(@PathVariable("id") Long id,@PathVariable("amount") Long amount);

    @PutMapping("/api/accounts/{id}/debit-money/{amount}")
    AccountResponse debitMoney(@PathVariable("id") Long id,@PathVariable("amount") Long amount);

}

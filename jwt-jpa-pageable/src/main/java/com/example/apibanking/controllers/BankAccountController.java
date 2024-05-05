package com.example.apibanking.controllers;

import com.example.apibanking.dto.AccountTransactionsRequestDto;
import com.example.apibanking.dto.TransactionResponseDTO;
import com.example.apibanking.entities.Transaction;
import com.example.apibanking.services.domain.AccountService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/bankAccount")
@RestController
public class BankAccountController {
    AccountService accountService;

    @Value("${microservice.sucursal}")
    private String sucursal;

    public BankAccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/getAccountTransactions")
    public Page<TransactionResponseDTO> getAccountTransactions(@RequestBody AccountTransactionsRequestDto accountTransactionsRequestDto){

        Page <Transaction> transactionsPage = accountService.findTransactionsByAccountNumber(
                accountTransactionsRequestDto.getAccountNumber(),
                PageRequest.of(accountTransactionsRequestDto.getPage(), accountTransactionsRequestDto.getSize()));
        return transactionsPage.map(transaction ->  TransactionResponseDTO.builder()
                .transactionDate(transaction.getTransactionDate())
                .transactionAmount(transaction.getTransactionAmount())
                .balance(transaction.getBalance())
                .description(transaction.getDescription())
                .sucursal(sucursal)
                .build());
    }
}

package com.example.apibanking.services.domain;

import com.example.apibanking.entities.Account;
import com.example.apibanking.entities.Transaction;
import com.example.apibanking.entities.User;
import com.example.apibanking.repositories.AccountRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{
    AccountRepository accountRepository;

    TransactionService transactionService;

    public AccountServiceImpl(AccountRepository accountRepository, TransactionService transactionService) {
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
    }

    @Override
    public Page<Transaction> findTransactionsByAccountNumber(String accountNumber, Pageable pageable) {
        Optional<Account> account = accountRepository.findAccountByAccountNumber(accountNumber);
        if (account.isPresent()){
            return  transactionService.findTransactionsByAccountId(account.get().getId(), pageable);
        } else
            return null;
    }

    @Override
    public Account createAccount(String accountNumber, BigDecimal accountBalance, User user) {
        Account account = Account.builder().accountNumber(accountNumber).accountBalance(accountBalance).user(user).build();
        return accountRepository.save(account);
    }
}

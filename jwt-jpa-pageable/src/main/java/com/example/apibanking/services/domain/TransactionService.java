package com.example.apibanking.services.domain;

import com.example.apibanking.entities.Account;
import com.example.apibanking.entities.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Date;

public interface TransactionService {
    public Page<Transaction> findTransactionsByAccountId(Long accountId, Pageable pageable);

    public Transaction createTransaction();

    void createTransaction(Date date, BigDecimal amount, BigDecimal balance, String description, Account account);
}

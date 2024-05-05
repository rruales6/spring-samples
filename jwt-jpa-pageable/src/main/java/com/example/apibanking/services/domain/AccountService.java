package com.example.apibanking.services.domain;

import com.example.apibanking.entities.Account;
import com.example.apibanking.entities.Transaction;
import com.example.apibanking.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    public Page<Transaction> findTransactionsByAccountNumber(String accountNumber, Pageable pageable);

    public Account createAccount(String accountNumber, BigDecimal AccountBalance, User user);
}

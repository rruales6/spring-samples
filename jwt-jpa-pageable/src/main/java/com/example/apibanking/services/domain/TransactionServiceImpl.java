package com.example.apibanking.services.domain;

import com.example.apibanking.entities.Account;
import com.example.apibanking.entities.Transaction;
import com.example.apibanking.repositories.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class TransactionServiceImpl implements TransactionService{
    TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Page<Transaction> findTransactionsByAccountId(Long accountId, Pageable pageable) {
        return transactionRepository.findByAccountId(accountId, pageable);
    }

    @Override
    public Transaction createTransaction() {
        return null;
    }

    @Override
    public void createTransaction(Date date, BigDecimal amount, BigDecimal balance, String description, Account account) {
        Transaction transaction = Transaction.builder().transactionDate(date).transactionAmount(amount)
                .balance(balance).description(description).account(account).build();

        transactionRepository.save(transaction);
    }
}

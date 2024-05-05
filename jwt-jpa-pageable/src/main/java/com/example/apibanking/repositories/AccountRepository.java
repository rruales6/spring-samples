package com.example.apibanking.repositories;

import com.example.apibanking.entities.Account;
import com.example.apibanking.entities.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Optional<Account> findAccountByAccountNumber(String number);
}

package com.example.apibanking.seeders;

import com.example.apibanking.dto.RegisterUserDTO;
import com.example.apibanking.entities.Account;
import com.example.apibanking.entities.Transaction;
import com.example.apibanking.entities.User;
import com.example.apibanking.services.domain.AccountService;
import com.example.apibanking.services.AuthenticationService;
import com.example.apibanking.services.domain.TransactionService;
import com.example.apibanking.services.UserService;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class DatabaseSeeder {

    AuthenticationService authenticationService;
    AccountService accountService;

    TransactionService transactionService;

    UserService userService;

    public DatabaseSeeder(AuthenticationService authenticationService, AccountService accountService, TransactionService transactionService, UserService userService) {
        this.authenticationService = authenticationService;
        this.accountService = accountService;
        this.transactionService = transactionService;
        this.userService = userService;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        RegisterUserDTO registerUserDto = RegisterUserDTO.builder().identification("1727275610")
                .fullName("Jose Ruales").password("Password").build();
        User user =authenticationService.createSecureUser(registerUserDto);

        Account account = Account.builder().accountNumber("AAA5565").accountBalance(new BigDecimal("0")).user(user).build();
        List transactions = new ArrayList<Transaction>();

        transactions.add(Transaction.builder().transactionDate(new Date()).transactionAmount(new BigDecimal("10")).balance(new BigDecimal("10")).description("test").account(account).build());
        transactions.add(Transaction.builder().transactionDate(new Date()).transactionAmount(new BigDecimal("10")).balance(new BigDecimal("20")).description("test").account(account).build());
        transactions.add(Transaction.builder().transactionDate(new Date()).transactionAmount(new BigDecimal("10")).balance(new BigDecimal("30")).description("test").account(account).build());
        transactions.add(Transaction.builder().transactionDate(new Date()).transactionAmount(new BigDecimal("10")).balance(new BigDecimal("40")).description("test").account(account).build());
        transactions.add(Transaction.builder().transactionDate(new Date()).transactionAmount(new BigDecimal("10")).balance(new BigDecimal("50")).description("test").account(account).build());
        transactions.add(Transaction.builder().transactionDate(new Date()).transactionAmount(new BigDecimal("10")).balance(new BigDecimal("60")).description("test").account(account).build());
        transactions.add(Transaction.builder().transactionDate(new Date()).transactionAmount(new BigDecimal("10")).balance(new BigDecimal("70")).description("test").account(account).build());
        transactions.add(Transaction.builder().transactionDate(new Date()).transactionAmount(new BigDecimal("10")).balance(new BigDecimal("80")).description("test").account(account).build());
        transactions.add(Transaction.builder().transactionDate(new Date()).transactionAmount(new BigDecimal("10")).balance(new BigDecimal("90")).description("test").account(account).build());
        transactions.add(Transaction.builder().transactionDate(new Date()).transactionAmount(new BigDecimal("10")).balance(new BigDecimal("100")).description("test").account(account).build());

        user.setAccounts(Arrays.asList(account));
        account.setTransactions(transactions);
        userService.createUser(user);


    }
}

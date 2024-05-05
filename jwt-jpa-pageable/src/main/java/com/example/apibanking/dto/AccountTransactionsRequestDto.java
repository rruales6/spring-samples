package com.example.apibanking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccountTransactionsRequestDto extends PageableRequestDTO{
    String identification;
    String accountNumber;

    @Override
    public String toString() {
        return "AccountTransactionsRequestDto{" +
                "identification='" + identification + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", page='" + page + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}

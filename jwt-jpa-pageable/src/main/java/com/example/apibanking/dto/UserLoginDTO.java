package com.example.apibanking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserLoginDTO {
    private String identification;
    private String password;

    @Override
    public String toString() {
        return "UserLoginDTO{" +
                "identification='" + identification + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

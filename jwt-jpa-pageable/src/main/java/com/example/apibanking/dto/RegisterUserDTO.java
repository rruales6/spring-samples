package com.example.apibanking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class RegisterUserDTO {
    private String identification;
    private String password;
    private String fullName;

    @Override
    public String toString() {
        return "RegisterUserDTO{" +
                "identification='" + identification + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}

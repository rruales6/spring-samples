package com.example.apibanking.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserLoginResponseDTO {
    private String token;
    private long expiresIn;

    @Override
    public String toString() {
        return "UserLoginResponseDTO{" +
                "token='" + token + '\'' +
                ", expiresIn=" + expiresIn +
                '}';
    }
}

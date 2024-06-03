package com.microservice.notificationapi.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import java.util.Base64;

@Service
@Getter
@Setter
public class SignatureServiceImpl implements SignatureService{

    private Mac mac;

    public SignatureServiceImpl(Mac mac) {
        this.mac = mac;
    }

    @Override
    public String signMD5WithSecret(String input) {
        byte[] hashBytes = mac.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(hashBytes);
    }


}

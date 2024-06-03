package com.microservice.notificationapi.service;

public interface SignatureService {
    public String signMD5WithSecret(String input);
}

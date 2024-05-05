package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApplicationConfiguration {

    @Bean
    WebClient defaultWebClientBuilder(){
        return WebClient.create();
    }
}

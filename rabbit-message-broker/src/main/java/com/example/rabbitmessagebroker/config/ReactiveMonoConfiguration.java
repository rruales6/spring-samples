package com.example.rabbitmessagebroker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ReactiveMonoConfiguration {
    @Bean
    WebClient defaultWebClientBuilder(){
        return WebClient.create();
    }
}

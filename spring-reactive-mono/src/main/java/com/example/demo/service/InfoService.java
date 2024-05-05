package com.example.demo.service;

import com.example.demo.DTO.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class InfoService {

    @Autowired
    private WebClient webClient;

    public Mono<Info> getWeatherData() {
        return webClient.get().uri("https://rickandmortyapi.com/api/character/70").retrieve().bodyToMono(Info.class);
    }
}

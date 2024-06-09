package com.example.demo.service;

import com.example.demo.DTO.Info;
import com.example.demo.DemoApplication;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class InfoService {



    @Autowired
    private WebClient webClient;

    public Mono<Info> getWeatherData() {
        return webClient.get().uri("https://rickandmortyapi.com/apiI/character/70")
                .retrieve().bodyToMono(Info.class).doOnNext()
                .doOnError(Exception.class, e -> {
                    if (e instanceof  WebClientResponseException rse){
                        log.error("error rse test:"+rse.getResponseBodyAsString());
                    }else
                        log.error("error test: "+e);
                });
    }
}

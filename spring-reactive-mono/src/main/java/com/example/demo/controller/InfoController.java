package com.example.demo.controller;


import com.example.demo.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class InfoController {

    @Autowired
    private InfoService infoService;

    @GetMapping("/info")
    public Mono<String> getWeather() {
        return infoService.getWeatherData().map(infoData -> "The name is " + infoData.getName() + " and state is  " + infoData.getStatus() + "");
    }
}


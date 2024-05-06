package com.example.rabbitmessagebroker.controller;

import com.example.rabbitmessagebroker.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ServerController {

    private QueueService queueService;

    public ServerController(QueueService queueService) {
        this.queueService = queueService;
    }


    @GetMapping("/send")
    public Mono<String> send(@RequestParam("message") String message) {
        queueService.sendMessage("","my-queue",message);
        return  Mono.just("Mensaje encolado");
    }
}

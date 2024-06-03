package com.microservice.notificationapi.controller;

import com.microservice.notificationapi.dto.notification.receive.NotificationRequestDto;
import com.microservice.notificationapi.dto.notification.receive.NotificationResponseDto;
import com.microservice.notificationapi.service.domain.NotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("notification/")
public class NotificationController {

    NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/send")
    public Mono<NotificationResponseDto> save(@RequestBody NotificationRequestDto notificationRequestDto) throws JsonProcessingException {
        return notificationService.receiveNotification(notificationRequestDto);
    }
}

package com.microservice.notificationapi.controller;

import com.microservice.notificationapi.dto.ApplicationSaveRequest;
import com.microservice.notificationapi.dto.notification.receive.NotificationResponseDto;
import com.microservice.notificationapi.service.domain.AplicationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/notification/application")
public class ApplicationController {

    AplicationService aplicationService;

    public ApplicationController(AplicationService aplicationService) {
        this.aplicationService = aplicationService;
    }

    @PostMapping("/save")
    public Mono<NotificationResponseDto> save(@RequestBody ApplicationSaveRequest applicationSaveRequest){
        return aplicationService.save(applicationSaveRequest);
    }

}

package com.microservice.notificationapi.controller;

import com.microservice.notificationapi.dto.SenderConfigSaveRequest;
import com.microservice.notificationapi.dto.notification.receive.NotificationResponseDto;
import com.microservice.notificationapi.service.domain.SenderConfigService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/notification/sender-config")
public class SenderConfigController {

    SenderConfigService senderConfigService;

    public SenderConfigController(SenderConfigService senderConfigService) {
        this.senderConfigService = senderConfigService;
    }

    @PostMapping("/save")
    public Mono<NotificationResponseDto> save(@RequestBody SenderConfigSaveRequest senderConfigSaveRequest){
        return senderConfigService.save(senderConfigSaveRequest);
    }
}

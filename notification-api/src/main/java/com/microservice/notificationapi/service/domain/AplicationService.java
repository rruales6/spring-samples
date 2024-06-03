package com.microservice.notificationapi.service.domain;

import com.microservice.notificationapi.dto.ApplicationSaveRequest;
import com.microservice.notificationapi.dto.notification.receive.NotificationResponseDto;
import com.microservice.notificationapi.entity.Application;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface AplicationService {
    public Mono<NotificationResponseDto> save(ApplicationSaveRequest applicationSaveRequest);

    Optional<Application> findAplicationByName(String name);
}

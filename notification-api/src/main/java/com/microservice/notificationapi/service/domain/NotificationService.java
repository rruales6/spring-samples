package com.microservice.notificationapi.service.domain;

import com.microservice.notificationapi.dto.notification.receive.NotificationRequestDto;
import com.microservice.notificationapi.dto.notification.receive.NotificationResponseDto;
import com.microservice.notificationapi.dto.notification.shared.NotificationQueueElement;
import com.fasterxml.jackson.core.JsonProcessingException;
import reactor.core.publisher.Mono;

public interface NotificationService {

    void send(NotificationQueueElement notificationQueueElement) throws JsonProcessingException;

    public Mono<NotificationResponseDto> receiveNotification(NotificationRequestDto notificationRequestDtoMono) throws JsonProcessingException;

}

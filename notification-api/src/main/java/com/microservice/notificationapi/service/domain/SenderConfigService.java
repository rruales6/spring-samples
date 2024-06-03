package com.microservice.notificationapi.service.domain;

import com.microservice.notificationapi.dto.SenderConfigSaveRequest;
import com.microservice.notificationapi.dto.notification.receive.NotificationResponseDto;
import reactor.core.publisher.Mono;

public interface SenderConfigService {
    public Mono<NotificationResponseDto> save(SenderConfigSaveRequest senderConfigSaveRequest);
}

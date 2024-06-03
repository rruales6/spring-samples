package com.microservice.notificationapi.service.domain;

import com.microservice.notificationapi.dto.notification.shared.NotificationQueueElement;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface SMSSenderService {
    public void send(NotificationQueueElement notificationQueueElement) throws JsonProcessingException;
}

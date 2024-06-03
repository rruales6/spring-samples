package com.microservice.notificationapi.service.domain;

import com.microservice.notificationapi.dto.notification.shared.NotificationQueueElement;

public interface EmailSenderService {
    public void send(NotificationQueueElement notificationQueueElement);
}

package com.microservice.notificationapi.service.domain;

import com.microservice.notificationapi.dto.notification.shared.NotificationQueueElement;
import com.microservice.notificationapi.entity.NotificationType;
import com.fasterxml.jackson.core.JsonProcessingException;

public class SenderFacade {
    SMSSenderService smsSenderService;
    EmailSenderService emailSenderService;

    public SenderFacade(SMSSenderService smsSenderService, EmailSenderService emailSenderService) {
        this.smsSenderService = smsSenderService;
        this.emailSenderService = emailSenderService;
    }

    public void  send(NotificationQueueElement notificationQueueElement) throws JsonProcessingException {
        NotificationType notificationType = NotificationType.valueOf(notificationQueueElement.getType());
        switch (notificationType){
            case SMS -> smsSenderService.send(notificationQueueElement);
            case EMAIL -> emailSenderService.send(notificationQueueElement);
            default -> throw new IllegalArgumentException("Unknown Processing for notification type: " + notificationType.toString());
        }
    }
}

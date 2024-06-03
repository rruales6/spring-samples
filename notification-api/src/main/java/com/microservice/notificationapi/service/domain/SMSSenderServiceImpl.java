package com.microservice.notificationapi.service.domain;

import com.microservice.notificationapi.dto.notification.shared.NotificationQueueElement;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

@Service
public class SMSSenderServiceImpl  implements SMSSenderService{
    @Override
    public void send(NotificationQueueElement notificationQueueElement) throws JsonProcessingException {

    }
}

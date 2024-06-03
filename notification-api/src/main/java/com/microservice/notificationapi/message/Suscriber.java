package com.microservice.notificationapi.message;

import com.microservice.notificationapi.dto.notification.shared.NotificationQueueElement;
import com.microservice.notificationapi.service.domain.NotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Suscriber {

    private NotificationService notificationService;

    private ObjectMapper objectMapper  = new ObjectMapper();

    public Suscriber(NotificationService notificationService) {
        this.notificationService = notificationService;
    }



    @RabbitListener(queues = "${microservice.queue.name}",concurrency = "10")
    public void handleMessage(@Payload String message) throws JsonProcessingException {
        log.debug("Received message: " + message);
        NotificationQueueElement notificationQueueElement =  objectMapper.readValue(message, NotificationQueueElement.class);
        notificationService.send(notificationQueueElement);
    }


}

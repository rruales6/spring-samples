package com.microservice.notificationapi.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Publisher {

    @Value("${microservice.queue.routing-key}")
    private String routingKey;

    @Value("${microservice.queue.exchange}")
    private String exchange;

    private RabbitTemplate rabbitTemplate;


    public Publisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Object message)  {
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }

}

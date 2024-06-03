package com.microservice.notificationapi.service;

import com.microservice.notificationapi.entity.Application;
import com.microservice.notificationapi.entity.Notification;
import com.microservice.notificationapi.entity.SenderConfig;

import java.util.Optional;

public interface RWService{
    Optional<Application> findAplicationByName(String name);

    Optional<SenderConfig> findByApplicationId(int applicationId);

    Notification saveNotificationRecord(Notification notification);

    Optional<Notification> findNotificationById(Long notification);
}

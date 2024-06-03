package com.microservice.notificationapi.service;

import com.microservice.notificationapi.entity.Application;
import com.microservice.notificationapi.entity.Notification;
import com.microservice.notificationapi.entity.SenderConfig;
import com.microservice.notificationapi.repositories.ApplicationRepository;
import com.microservice.notificationapi.repositories.NotificationRepository;
import com.microservice.notificationapi.repositories.SenderConfigRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RWServiceImpl implements RWService{
    ApplicationRepository applicationRepository;
    NotificationRepository notificationRepository;
    SenderConfigRepository senderConfigRepository;

    public RWServiceImpl(ApplicationRepository applicationRepository, NotificationRepository notificationRepository, SenderConfigRepository senderConfigRepository) {
        this.applicationRepository = applicationRepository;
        this.notificationRepository = notificationRepository;
        this.senderConfigRepository = senderConfigRepository;
    }
    @Override
    public Optional<Application> findAplicationByName(String name){
        return applicationRepository.findByName(name);
    }

    @Override
    public Optional<SenderConfig> findByApplicationId(int applicationId) {
        return senderConfigRepository.findByApplicationId(applicationId);
    }

    @Override
    public Notification saveNotificationRecord(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Optional<Notification> findNotificationById(Long notificationId) {
        return notificationRepository.findById(notificationId);
    }




}

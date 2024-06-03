package com.microservice.notificationapi.repositories;

import com.microservice.notificationapi.entity.Notification;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface NotificationRepository extends CrudRepository<Notification,Long> {

    Notification save(Notification entity);

    Optional<Notification> findById(Long id);
}

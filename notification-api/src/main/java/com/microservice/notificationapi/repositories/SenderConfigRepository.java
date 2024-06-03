package com.microservice.notificationapi.repositories;


import com.microservice.notificationapi.entity.SenderConfig;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SenderConfigRepository extends CrudRepository<SenderConfig,Integer> {
    Optional<SenderConfig> findById(Integer id);

    Optional<SenderConfig> findByApplicationId(int id);

    boolean existsById(int id);

    SenderConfig save(SenderConfig senderConfig);
}

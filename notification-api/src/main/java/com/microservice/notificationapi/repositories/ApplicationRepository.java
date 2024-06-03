package com.microservice.notificationapi.repositories;


import com.microservice.notificationapi.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ApplicationRepository extends JpaRepository<Application,Integer> {

    boolean existsById(int id);
    Optional<Application> findByName(String name);
    Optional<Application> findById(Integer id);

    Application save(Application application);


}

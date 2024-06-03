package com.microservice.notificationapi.service.domain;

import com.microservice.notificationapi.dto.ApplicationSaveRequest;
import com.microservice.notificationapi.dto.notification.receive.NotificationResponseDto;
import com.microservice.notificationapi.entity.Application;
import com.microservice.notificationapi.exception.NotificationException;
import com.microservice.notificationapi.repositories.ApplicationRepository;
import com.microservice.notificationapi.utils.AppUtils;
import com.microservice.notificationapi.utils.ResponseStateEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Slf4j
@Service
public class AplicationServiceImpl implements AplicationService {

    ApplicationRepository applicationRepository;

    public AplicationServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public Mono<NotificationResponseDto> save(ApplicationSaveRequest applicationSaveRequest) {


        Application application;
        if (applicationSaveRequest.getId().isEmpty()) {
            application = Application.builder().name(applicationSaveRequest.getName())
                    .description(applicationSaveRequest.getDescription())
                    .createdAt(AppUtils.getTimeStamp().toLocalDateTime())
                    .build();
        } else {
            Optional<Application> applicationOptional = applicationRepository.findById(Integer.parseInt(applicationSaveRequest.getId()));
            if (applicationOptional.isPresent()){
                application = applicationRepository.findById(Integer.parseInt(applicationSaveRequest.getId())).get();
                application.setName(applicationSaveRequest.getName());
                application.setDescription(applicationSaveRequest.getDescription());
                application.setUpdatedAt(AppUtils.getTimeStamp().toLocalDateTime());
            }else{
                throw new NotificationException(AppUtils.RECORD_NOT_FOUND);
            }

        }
        application = applicationRepository.save(application);

        return Mono.just(
                NotificationResponseDto.builder()
                        .status(ResponseStateEnum.SUCCESS.toString())
                        .Message(ResponseStateEnum.SUCCESS.value() + application.getId().toString())
                        .build()
        );


    }


    @Override
    public Optional<Application> findAplicationByName(String name){
        return applicationRepository.findByName(name);
    }
}

package com.microservice.notificationapi.service.domain;

import com.microservice.notificationapi.dto.SenderConfigSaveRequest;
import com.microservice.notificationapi.dto.notification.receive.NotificationResponseDto;
import com.microservice.notificationapi.entity.Application;
import com.microservice.notificationapi.entity.NotificationType;
import com.microservice.notificationapi.entity.SenderConfig;
import com.microservice.notificationapi.exception.NotificationException;
import com.microservice.notificationapi.repositories.ApplicationRepository;
import com.microservice.notificationapi.repositories.SenderConfigRepository;
import com.microservice.notificationapi.utils.AppUtils;
import com.microservice.notificationapi.utils.ResponseStateEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
public class SenderConfigServiceImpl implements SenderConfigService{

    ApplicationRepository applicationRepository;
    SenderConfigRepository senderConfigRepository;

    public SenderConfigServiceImpl(ApplicationRepository applicationRepository, SenderConfigRepository senderConfigRepository) {
        this.applicationRepository = applicationRepository;
        this.senderConfigRepository = senderConfigRepository;
    }

    @Override
    @Transactional
    public Mono<NotificationResponseDto> save(SenderConfigSaveRequest senderConfigSaveRequest) {

        SenderConfig senderConfig;
        Application application = applicationRepository.findById(Integer.parseInt(senderConfigSaveRequest.getIdApplication()))
                .get();
        if (senderConfigSaveRequest.getId().isEmpty()){
            senderConfig = SenderConfig.builder()
                    .application(application)
                    .notificationType(NotificationType.fromValue(senderConfigSaveRequest.getNotificationType()))
                    .enabled(senderConfigSaveRequest.isEnabled())
                    .isTestEnv(senderConfigSaveRequest.isTestEnv())
                    .isHtml(senderConfigSaveRequest.isHtml())
                    .createdAt(AppUtils.getTimeStamp().toLocalDateTime())
                    .build();
        }else {
            if (senderConfigRepository.existsById(Integer.parseInt(senderConfigSaveRequest.getId()))){
                senderConfig = senderConfigRepository.findById(Integer.parseInt(senderConfigSaveRequest.getId())).get();
                senderConfig.setApplication(application);
                senderConfig.setNotificationType(NotificationType.fromValue(senderConfigSaveRequest.getNotificationType()));
                senderConfig.setEnabled(senderConfigSaveRequest.isEnabled());
                senderConfig.setTestEnv(senderConfigSaveRequest.isTestEnv());
                senderConfig.setHtml(senderConfigSaveRequest.isHtml());
                senderConfig.setUpdatedAt(AppUtils.getTimeStamp().toLocalDateTime());
            }else {
                throw new NotificationException(AppUtils.RECORD_NOT_FOUND);
            }
        }
        senderConfig = senderConfigRepository.save(senderConfig);

        return Mono.just(
                NotificationResponseDto.builder()
                        .status(ResponseStateEnum.SUCCESS.toString())
                        .Message(ResponseStateEnum.SUCCESS.value() + senderConfig.getId().toString())
                        .build()
        );
    }
}

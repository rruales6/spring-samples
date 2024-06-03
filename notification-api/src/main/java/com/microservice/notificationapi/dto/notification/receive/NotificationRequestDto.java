package com.microservice.notificationapi.dto.notification.receive;


import com.microservice.notificationapi.entity.NotificationType;
import com.microservice.notificationapi.validators.ValueOfEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class NotificationRequestDto {
    @NotBlank
    String appId;
    @ValueOfEnum(enumClass = NotificationType.class)
    @NotBlank
    String type;
    @NotBlank
    String to;
    String subject;
    @NotBlank
    String message;
}

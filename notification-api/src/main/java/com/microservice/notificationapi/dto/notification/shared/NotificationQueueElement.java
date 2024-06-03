package com.microservice.notificationapi.dto.notification.shared;

import com.microservice.notificationapi.entity.NotificationType;
import com.microservice.notificationapi.validators.ValueOfEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationQueueElement {
    Long id;
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

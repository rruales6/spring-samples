package com.microservice.notificationapi.dto.notification.receive;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponseDto {
    String status;
    String Message;
}

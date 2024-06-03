package com.microservice.notificationapi.dto.notification.send.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendRequest {
    private Enviar enviar;
}

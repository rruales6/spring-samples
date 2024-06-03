package com.microservice.notificationapi.dto.notification.send.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthRequest {
    @JsonProperty("Emisor")
    String emisor;
    @JsonProperty("Password")
    String password;
}

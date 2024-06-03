package com.microservice.notificationapi.dto.notification.send.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor @Getter @Setter @ToString
public class SendResponse {
    @JsonProperty("Resenviar")
    private Resenviar resenviar;
}

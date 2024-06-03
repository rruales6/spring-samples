package com.microservice.notificationapi.dto.notification.send.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ErrorDetail {
    @JsonProperty("Dest")
    String dest;
    @JsonProperty("Text")
    String text;
}

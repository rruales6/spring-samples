package com.microservice.notificationapi.dto.notification.send.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Enviar {
    @JsonProperty("servidor")
    public Servidor servidor;

    @JsonProperty("Mensaje")
    public String mensaje;

    @JsonProperty("Dest")
    public String dest;
}
package com.microservice.notificationapi.dto.notification.send.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Servidor {
    @JsonProperty("Servicio")
    public String servicio;

    @JsonProperty("Emisor")
    public String emisor;

    @JsonProperty("Login")
    public String login;

    @JsonProperty("Pwd")
    public String pwd;

    @JsonProperty("NumDest")
    public String numDest;

    @JsonProperty("Referencia")
    public String referencia;

    @JsonProperty("FechaEnv")
    public String fechaEnv;

    @JsonProperty("HoraEnv")
    public String horaEnv;

    @JsonProperty("NombrePC")
    public String nombrePC;

    @JsonProperty("Key")
    public String key;
}
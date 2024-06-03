package com.microservice.notificationapi.dto.notification.send.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.stream.Collectors;

@NoArgsConstructor @Getter @Setter @ToString
public class Resenviar {
    @JsonProperty("Errores")
    private String errores;
    @JsonProperty("Error")
    private ArrayList<ErrorDetail> errors;

    public String getErrorDetails(){
        return errors.stream().map(ErrorDetail::getText).collect(Collectors.joining(", "));
    }
}

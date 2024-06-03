package com.microservice.notificationapi.dto;

import lombok.*;

@Data
public class ApplicationSaveRequest {
    String id;
    String name;

    String description;
}

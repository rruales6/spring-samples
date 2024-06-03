package com.microservice.notificationapi.dto;

import lombok.Data;

@Data
public class SenderConfigSaveRequest {
    String id;
    String idApplication;
    String notificationType;
    boolean enabled;
    boolean isTestEnv;
    boolean isHtml;
}

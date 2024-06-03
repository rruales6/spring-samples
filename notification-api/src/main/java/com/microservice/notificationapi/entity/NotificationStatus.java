package com.microservice.notificationapi.entity;

public enum NotificationStatus {
    Q("QUEUED",false),S("SENT", false),E("FAILED", false),R("REQUEUED", false);

    String status;
    boolean isSent;

    NotificationStatus(String status, boolean isSent) {
        this.status = status;
        this.isSent = isSent;
    }

    public boolean isSent (){
        return isSent;
    }
}

package com.microservice.notificationapi.entity;

public enum NotificationType {

    SMS("Notificaciones por sms"), EMAIL("Notificaciones por email"), MS_TEAMS("Notificaciones por MS teams");

    String description;
    NotificationType(String s) {
        this.description = s;
    }

    public static NotificationType fromValue  (String s){
        for (NotificationType notificationType : NotificationType.values()) {
            if (notificationType.toString().equals(s)) {
                return notificationType;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + s);
    }
}

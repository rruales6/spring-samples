package com.microservice.notificationapi.utils;

public enum ResponseStateEnum {
    SUCCESS("Ok: "),
    ERROR("Error: ");

    String description;

    ResponseStateEnum(String description) {
        this.description = description;
    }

    public String value (){
        return  this.description;
    }

}

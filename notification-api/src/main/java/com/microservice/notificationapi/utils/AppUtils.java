package com.microservice.notificationapi.utils;

import com.microservice.notificationapi.exception.NotificationException;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class AppUtils {

    public static String RECORD_NOT_FOUND = "Registro no encontrado, revise la petición";

    static DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    static DateTimeFormatter formatterDateSMSProvider = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    static DateTimeFormatter formatterTimeSMSProvider = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static int MAX_MESSAGE_CHARACTERS = 160;

    public static int MAX_ERROR_LENGTH = 255;

    public static String getTimeStampFormated(){
        return ZonedDateTime.now().format(formatterDateTime);
    }

    public static String getDate(){
        return ZonedDateTime.now().format(formatterDateSMSProvider);
    }

    public static String getTime(){
        return ZonedDateTime.now().format(formatterTimeSMSProvider);
    }

    public static ZonedDateTime getTimeStamp(){return ZonedDateTime.now();}

    public static String getHostName(){
        String hostname;
        try {
            hostname =  InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            hostname = "notification-server-com";
        }

        return hostname;
    }

    public static String formatPhoneNumber(String number){
        if (number.length()==9){
            return number = "0" + number;
        }
        if (number.length()==10){
            return number;
        }
        throw new NotificationException("Formato de numero desconocido");
    }

    public static String truncateMessageString(String string ){
        return string.length()<= MAX_MESSAGE_CHARACTERS ? string : string.substring(0, MAX_MESSAGE_CHARACTERS);
    }

    public static String truncateErrorMessage(String string ){
        return string.length()<= MAX_ERROR_LENGTH ? string : string.substring(0, MAX_ERROR_LENGTH);
    }

}

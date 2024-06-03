package com.microservice.notificationapi.exception;

import com.microservice.notificationapi.dto.notification.receive.NotificationResponseDto;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
@Getter
@Setter
@Slf4j
public class GlobalExceptionHandler {
    private static final String NOTIFICATION_EXCEPTION_MESSAGE = "Error while receiving message";
    private static final String GENERIC_MESSAGE = "Error while processing notification";
    @ExceptionHandler(NotificationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Mono<NotificationResponseDto> handleReceiveNotificationException(NotificationException notificationException) {
        String message = notificationException.getMessage();
        log.error(String.format("%s: %s", NOTIFICATION_EXCEPTION_MESSAGE, message), notificationException);

        return Mono.just(NotificationResponseDto.builder()
                .status("Error")
                .Message(message)
                .build());

    }


    @ExceptionHandler(Throwable.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Mono<NotificationResponseDto> handleReceiveNotificationException(Throwable throwable) {
        String message = throwable.getMessage();
        NotificationResponseDto notificationResponseDto = NotificationResponseDto.builder()
                .status("Error")
                .Message(message)
                .build();

        log.error(String.format("%s: %s", GENERIC_MESSAGE, message), throwable);

        return Mono.just(notificationResponseDto);


    }


}

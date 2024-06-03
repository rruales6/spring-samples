package com.microservice.notificationapi.service.domain;

import com.microservice.notificationapi.dto.notification.receive.NotificationResponseDto;
import com.microservice.notificationapi.dto.notification.send.request.AuthRequest;
import com.microservice.notificationapi.dto.notification.send.request.Enviar;
import com.microservice.notificationapi.dto.notification.send.request.SendRequest;
import com.microservice.notificationapi.dto.notification.send.request.Servidor;
import com.microservice.notificationapi.dto.notification.send.response.SendResponse;
import com.microservice.notificationapi.dto.notification.shared.NotificationQueueElement;
import com.microservice.notificationapi.dto.notification.receive.NotificationRequestDto;
import com.microservice.notificationapi.entity.*;
import com.microservice.notificationapi.exception.NotificationException;
import com.microservice.notificationapi.message.Publisher;
import com.microservice.notificationapi.service.RWService;
import com.microservice.notificationapi.service.SignatureService;
import com.microservice.notificationapi.utils.AppUtils;
import com.microservice.notificationapi.utils.ResponseStateEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {

    private RWService rwService;

    private AplicationService aplicationService;
    private Publisher publisher;

    private SignatureService signatureService;

    private WebClient webClient;

    private ObjectMapper objectMapper;

    private final ObjectMapper mapper = new ObjectMapper();


    @Value("${microservice.provider.parameters.servicio}")
    private String servicio;
    @Value("${microservice.provider.parameters.emisor}")
    private String emisor;
    @Value("${microservice.provider.parameters.login}")
    private String login;
    @Value("${microservice.provider.parameters.password}")
    private String password;
    @Value("${microservice.provider.message-signature.secret-key}")
    private String secretKey;

    @Value("${microservice.provider.parameters.uri-auth}")
    private String uriAuth;
    @Value("${microservice.provider.parameters.uri-send}")
    private String uriSend;

    @Value("${spring.rabbitmq.listener.simple.retry.max-attempts}")
    private int maxAttempts;


    @Value("${microservice.provider.parameters.test-message}")
    private String prefixTest;


    public NotificationServiceImpl(RWService rwService, Publisher publisher, SignatureService signatureService, WebClient webClient, ObjectMapper objectMapper, AplicationService aplicationService) {
        this.rwService = rwService;
        this.publisher = publisher;
        this.signatureService = signatureService;
        this.webClient = webClient;
        this.objectMapper = objectMapper;
        this.aplicationService = aplicationService;
    }

    @Override
    @Transactional(propagation = Propagation.NEVER)
    public void send(NotificationQueueElement notificationQueueElement) throws JsonProcessingException {
        //TODO : notification type facade
        NotificationStatus notificationStatus = NotificationStatus.S;
        Notification notification;
        if (Objects.isNull(notificationQueueElement.getId())) {
            throw new NotificationException("A Database Notification record is needed");
        } else {
            Optional<Notification> optionalNotification =rwService.findNotificationById(notificationQueueElement.getId());
            if (optionalNotification.isPresent()){
                notification = optionalNotification.get();
            }else {
                throw new NotificationException(AppUtils.RECORD_NOT_FOUND);
            }
        }


        String sendRequest = buildSendRequest(notificationQueueElement, notification.getId());

        log.debug("Sms Provider rq: "+ sendRequest);

        String token = getTokenAuth(notification);
        String response = webClient.method(HttpMethod.POST)
                .uri(uriSend)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(httpHeaders -> httpHeaders.add("Authorization", "Bearer " + token))
                .body(Mono.just(sendRequest), String.class)
                .retrieve()
                .bodyToMono(String.class)
                .doOnError(Exception.class, (Exception error) -> {
                    if(error instanceof WebClientResponseException rse){
                        notification.setResponse(AppUtils.truncateErrorMessage(rse.getResponseBodyAsString()));
                        log.error("Sms Provider error response+"+ rse.getResponseBodyAsString());
                    }else if(error instanceof WebClientRequestException rqe){
                        notification.setResponse(AppUtils.truncateErrorMessage(rqe.getCause().toString()));
                        log.error("Sms Provider auth timeout: "+ rqe.getCause());
                    }else {
                        notification.setResponse(error.getMessage());
                        log.error("Unknown error produced when calling Sms Provider for Auth", error);
                    }
                    NotificationStatus status = getNewNotificationStatus(notification);
                    updateNotificationStatus(notification, status);
                })
                .block();
        log.debug("Sms Provider rs: "+response);
        SendResponse sendResponse = objectMapper.readValue(response, SendResponse.class);


        if (Objects.isNull(sendResponse) || !sendResponse.getResenviar().getErrores().equals("0") ){
            String error = "Message not sent: ";
            if (Objects.nonNull(sendResponse)){
                error = error + sendResponse.getResenviar().getErrorDetails();
                notification.setResponse(error);
            }
            log.error(error);
            notificationStatus = getNewNotificationStatus(notification);
        }
        updateNotificationStatus(notification, notificationStatus);
        if (!notificationStatus.equals(NotificationStatus.S))
            throw new NotificationException("Error during send request: Sms Provider");

    }



    public Mono<NotificationResponseDto> receiveNotification(NotificationRequestDto notificationRequestDto) throws JsonProcessingException {
        String recieved = Strings.EMPTY;

        Optional<Application> applicationOp = rwService.findAplicationByName(notificationRequestDto.getAppId());

        if (applicationOp.isEmpty()) {
            throw new NotificationException(AppUtils.RECORD_NOT_FOUND);
        }

        NotificationQueueElement notificationQueueElement = createNotificationQueueElement(notificationRequestDto, applicationOp.get());

        Notification notification = createNotificationRecord(notificationQueueElement);
        notificationQueueElement.setId(notification.getId());

        String message = mapper.writeValueAsString(notificationQueueElement);
        publisher.sendMessage(message);

        recieved = AppUtils.getTimeStampFormated();

        return Mono.just(
                NotificationResponseDto.builder()
                        .status(ResponseStateEnum.SUCCESS.toString())
                        .Message(ResponseStateEnum.SUCCESS.value() + recieved)
                        .build());
    }
    public NotificationStatus getNewNotificationStatus(Notification notification){
        updateNotificationRetries(notification);
        if (notification.getRetries() >= maxAttempts){
            return NotificationStatus.E;
        }
        return NotificationStatus.R;
    }
    public void updateNotificationStatus(Notification notification, NotificationStatus notificationStatus) {
        notification.setUpdatedAt(AppUtils.getTimeStamp().toLocalDateTime());
        notification.setStatus(notificationStatus);
        rwService.saveNotificationRecord(notification);
    }

    public void updateNotificationRetries(Notification notification){
            notification.setRetries(notification.getRetries()+1);
    }

    public String buildSendRequest(NotificationQueueElement notificationQueueElement, Long reference) throws JsonProcessingException {
        String separator = ";";
        String sourceMD5 = servicio + separator + secretKey + separator + emisor + separator + login + separator
                + password + separator + reference.toString();

        String hashMD5 = signatureService.signMD5WithSecret(sourceMD5);

        Servidor servidor = Servidor.builder()
                .servicio(servicio)
                .emisor(emisor)
                .login(login)
                .pwd(password)
                .numDest(String.valueOf(1))
                .referencia(reference.toString())
                .fechaEnv(AppUtils.getDate())
                .horaEnv(AppUtils.getTime())
                .nombrePC(AppUtils.getHostName())
                .key(hashMD5).build();

            Enviar enviar = Enviar.builder().servidor(servidor)
                .mensaje(notificationQueueElement.getMessage())
                .dest(notificationQueueElement.getTo()).build();

        return objectMapper.writeValueAsString(new SendRequest(enviar));
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Notification createNotificationRecord(NotificationQueueElement notificationQueueElement) {

        Optional<Application> optionalApp = aplicationService.findAplicationByName(notificationQueueElement.getAppId());

        if (optionalApp.isEmpty()){
            throw new NotificationException(AppUtils.RECORD_NOT_FOUND);
        }

        Application application = optionalApp.get();

        Notification notification = Notification.builder()
                .createdAt(AppUtils.getTimeStamp().toLocalDateTime())
                .application(application)
                .type(NotificationType.fromValue(notificationQueueElement.getType()))
                .dest(notificationQueueElement.getTo())
                .subject(notificationQueueElement.getSubject())
                .message(notificationQueueElement.getMessage())
                .status(NotificationStatus.Q)
                .retries(0).build();

        notification = rwService.saveNotificationRecord(notification);

        return notification;

    }

    public String getTokenAuth(Notification notification) throws JsonProcessingException {

        AuthRequest authRequest = AuthRequest.builder().emisor(emisor).password(password).build();
        String rq = objectMapper.writeValueAsString(authRequest);
        log.debug("Auth RQ: "+ rq );
        String token = webClient.method(HttpMethod.POST)
                .uri(uriAuth)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(rq), String.class)
                .retrieve()
                .bodyToMono(String.class)
                .doOnError(Exception.class, (Exception error) ->{
                    if(error instanceof WebClientResponseException rse){
                        notification.setResponse(AppUtils.truncateErrorMessage(rse.getResponseBodyAsString()));
                        log.error("Sms Provider auth response+"+ rse.getResponseBodyAsString());
                    }else if(error instanceof WebClientRequestException rqe){
                        notification.setResponse(AppUtils.truncateErrorMessage(rqe.getCause().toString()));
                        log.error("Sms Provider auth timeout: "+ rqe.getCause());
                    }else{
                            notification.setResponse(error.getMessage());
                            log.error("Unknown error produced when calling Sms Provider for Auth", error);
                    }
                    NotificationStatus status = getNewNotificationStatus(notification);
                    updateNotificationStatus(notification, status);
                } )
                .block();

        return objectMapper.readValue(token, String.class);
    }

    public NotificationQueueElement createNotificationQueueElement(NotificationRequestDto notificationRequestDto, Application application){

        Optional<SenderConfig> senderConfigOp =rwService.findByApplicationId(application.getId());

        if (senderConfigOp.isEmpty() || !senderConfigOp.get().isEnabled() ) {
            throw new NotificationException(AppUtils.RECORD_NOT_FOUND);
        }

        String message = notificationRequestDto.getMessage();

        if (senderConfigOp.get().isTestEnv()){
            message = prefixTest + " " + message;
        }

        return NotificationQueueElement.builder()
                .appId(notificationRequestDto.getAppId())
                .type(notificationRequestDto.getType())
                .to(AppUtils.formatPhoneNumber(notificationRequestDto.getTo()))
                .subject(notificationRequestDto.getSubject())
                .message(AppUtils.truncateMessageString(message))
                .build();
    }
}

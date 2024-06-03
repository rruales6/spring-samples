package com.microservice.notificationapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "NO_TAB_NOTIFICATION",schema = "NOTIFICATION")
@SequenceGenerator(name = "SEQ_NOTIFICATION", sequenceName = "NO_SEQ_TAB_NOTIFICATION", schema = "NOTIFICATION", allocationSize =1 )
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_NOTIFICATION")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne()
    @JoinColumn(name = "id_aplication")
    private Application application;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType type;

    @Column(nullable = false)
    private String dest;

    private String subject;

    @Column(nullable = false)
    private String message;

    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

    private String response;

    @Column(nullable = false)
    private int retries;

}

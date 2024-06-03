package com.microservice.notificationapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "NO_TAB_SENDER_CONFIG",schema = "NOTIFICATION" , uniqueConstraints = @UniqueConstraint(columnNames ={"id_aplication", "notificationType"} ))
@SequenceGenerator(name = "SEQ_SENDER_CONFIG", sequenceName = "NO_SEQ_TAB_SENDER_CONFIG", schema = "NOTIFICATION", allocationSize =1)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SenderConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SENDER_CONFIG")
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_aplication")
    private Application application;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    private boolean enabled;

    private boolean isTestEnv;

    private boolean isHtml;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

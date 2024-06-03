package com.microservice.notificationapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "NO_TAB_APPLICATION",schema = "NOTIFICATION")
@SequenceGenerator(name = "SEQ_APPLICATION", sequenceName = "NO_SEQ_TAB_APPLICATION", schema = "NOTIFICATION", allocationSize =1)
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_APPLICATION")
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

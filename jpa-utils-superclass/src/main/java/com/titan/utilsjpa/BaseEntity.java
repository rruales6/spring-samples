package com.titan.utilsjpa;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//base entity to define common fields id, create/modified user etc
// TODO: 9/7/23 add create/modified fields for user and date tracking
@MappedSuperclass
@Setter
@Getter
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_gen")
    private Long id;

    @Version
    private Integer version;
}

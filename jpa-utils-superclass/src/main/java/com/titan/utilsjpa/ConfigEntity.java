package com.titan.utilsjpa;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Setter
@Getter
public class ConfigEntity extends BaseEntity{
    @Column(nullable = false)
    private String Code;
    @Column(nullable = false)
    private String Name;
    @Column(nullable = false)
    private boolean Active;
}

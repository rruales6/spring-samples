package com.titan.utilsjpa;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Setter
@Getter
public class ProcessEntity extends BaseEntity{
    @Column(nullable = false)
    private String number;

    // TODO: 7/7/23 add status  enum to hold process status
}

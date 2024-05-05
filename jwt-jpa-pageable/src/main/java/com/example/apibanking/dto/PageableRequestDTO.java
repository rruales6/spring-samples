package com.example.apibanking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageableRequestDTO {
    int page;
    int size;
}

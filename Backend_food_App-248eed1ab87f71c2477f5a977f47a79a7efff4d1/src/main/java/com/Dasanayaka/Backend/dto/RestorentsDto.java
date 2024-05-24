package com.Dasanayaka.Backend.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.List;

@Data
@Embeddable
public class RestorentsDto {
    private String titile;

    @Column(length=1000)
    private List<String> Images;

    private String description;

    private Long id;
}

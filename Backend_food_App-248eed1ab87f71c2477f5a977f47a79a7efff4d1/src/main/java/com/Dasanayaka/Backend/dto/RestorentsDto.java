package com.Dasanayaka.Backend.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.List;

@Data
@Embeddable
public class RestorentsDto {

    private String title; // Corrected the spelling

    @Column(length=1000)
    private List<String> images; // Followed Java naming conventions

    private String description;

    private Long id;

    public void setDescription(String _description) {
        this.description = _description;
    }

    public void setImages(Object _images) {
        if (_images instanceof List<?>) {
            for (Object image : (List<?>) _images) {
                if (!(image instanceof String)) {
                    throw new IllegalArgumentException("All elements of the image list should be strings.");
                }
            }
            this.images = (List<String>) _images;
        } else {
            throw new IllegalArgumentException("Images should be a list of strings.");
        }
    }

    public void setTitle(String  _name) {
        title= _name;
    }

    public void setId(Long restorentId) {
        id= restorentId;
    }
}

package com.Dasanayaka.Backend.request;


import com.Dasanayaka.Backend.model.IngreadiantItem;
import lombok.Data;

import javax.lang.model.element.Name;
import java.util.List;

@Data
public class CreateFoodRequest {

    private String name;
    private String Description;
    private Long Price;
    private Long category;
    private List<String> images;

    private Long RestaurantId;
    private boolean vegitarian;
    private boolean seasional;
    private List<IngreadiantItem> ingreadiants;

    public Object getDescription() {
        return  Description;
    }

    public Object getImages() {
        return images;
    }

    public Object getName() {
        return name;
    }

    public Object getPrice() {
        return Price;
    }
}

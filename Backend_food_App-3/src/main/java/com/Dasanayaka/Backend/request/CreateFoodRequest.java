package com.Dasanayaka.Backend.request;


import com.Dasanayaka.Backend.model.Catagory;
import com.Dasanayaka.Backend.model.IngreadiantItem;
import lombok.Data;

import javax.lang.model.element.Name;
import java.util.List;

@Data
public class CreateFoodRequest {

    private String name;
    private String Description;
    private Long Price;
    private Catagory category;
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

    public Object getIngrediants() {
        return  ingreadiants;
    }

    public Object isSeasional() {
        return  seasional;
    }

    public Object isVegitarin() {
        return  vegitarian;
    }

    public Long getRestaurantId() { return RestaurantId;
    }

    public Catagory getCategory() {
        return category;
    }
}

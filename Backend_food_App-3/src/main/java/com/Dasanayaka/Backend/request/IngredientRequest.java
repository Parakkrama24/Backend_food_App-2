package com.Dasanayaka.Backend.request;

import lombok.Data;

@Data
public class IngredientRequest {

     private String name;
     private  Long categoryId;
     private Long restaurantId;

    public Long getRestaurantId() {
        return  restaurantId;
    }

    public String getName() {
        return  name;
    }

    public Long getCategoryId() {
        return  categoryId;
    }
}

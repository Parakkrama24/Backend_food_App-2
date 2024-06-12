package com.Dasanayaka.Backend.request;


import lombok.Data;

@Data
public class IngredientCategoryRequest {

    private  String name;
    private Long restaurantId;


    public Long getRestaurantId() {
        return restaurantId;
    }

    public String getName() {
        return  name;
    }
}
